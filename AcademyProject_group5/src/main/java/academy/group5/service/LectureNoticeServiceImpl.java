package academy.group5.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.CancelLecture;
import academy.group5.dto.Lecture;
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.LectureNoticeSetTime;
import academy.group5.dto.etc.LecturePaging;
import academy.group5.dto.etc.UserLectureNotice;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.LectureNoticeRepo;
import academy.group5.repo.LectureRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class LectureNoticeServiceImpl implements LectureNoticeService{

	/** 한 페이지에 표시되는 공지의 수 */
	private final int NOTICE_MAX_PAGE = 5;
	
	@Autowired
	LectureService lecService;
	
	@Autowired
	LectureNoticeRepo notiRepo;
	
	@Autowired
	LectureRepo lecRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	@Override
	public List<UserLectureNotice> allLectureNoticeList(String userId, int page) {
		List<UserLectureNotice> noticeList = notiRepo.getAllLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, userId));
		
		for(UserLectureNotice noticeData : noticeList){
			String timeStr = noticeData.getNoticeTime();
			noticeData.setNoticeTime(timeStr.replaceAll(" ", "<br>"));
		}
		
		return noticeList;
	}

	@Override
	public List<LectureNotice> lectureNoticeList(Integer lectureId, Integer lectureClass, int page) {
	
		return notiRepo.getLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, lectureId, lectureClass));
	}

	@Override
	public LectureNotice lectureNoticeInfo(Integer lectureNoticeId) {
		LectureNotice noticeData = notiRepo.getLectureNoticeInfo(lectureNoticeId);
		
		if(noticeData == null){
			throw new WrongRequestException();
		}
		return noticeData;
	}
	
	@Override
	public boolean postNotice(LectureNotice noticeData, boolean sendGCM) {
		
		int result = notiRepo.setLectureNotice(noticeData);
		if(result != 1){
			throw new WrongRequestException();
		} 
		
		if(sendGCM){
			sendGCM(noticeData);
		}
		return true;
	}
	
	@Override
	public boolean postNotice(LectureNoticeSetTime lectureNoticeAndTime) {
		
		if(lectureNoticeAndTime.getIsTempDate() == null){
			throw new WrongRequestException("날짜가 설정되지 않았습니다.");
		}
	
		// 공지사항 정보, 새 강의 정보
		LectureNotice noticeData = new LectureNotice(lectureNoticeAndTime);
		LectureTime lectureData = new LectureTime(lectureNoticeAndTime);	
		
		Integer newLectureStart = lectureData.getLectureStart();
		Integer newLectureEnd = lectureData.getLectureEnd();
		if (newLectureStart != null && newLectureEnd != null
				&& newLectureStart > newLectureEnd){
			throw new WrongRequestException("시작교시보다 종료교시가 앞설 수 없습니다.");
		}

		// 강의 이름
		String lectureName = lecRepo.getLectureName(new Lecture(lectureData.getLectureId(), lectureData.getLectureClass()));
		
		// 기존 강의 정보
		LectureTime existingLectureData = lecRepo.getLectureTimeById(lectureData.getLectureTimeId());
		if(existingLectureData == null){
			throw new WrongRequestException();
		}
		// 기존 강의 시간
		Date existingLectureTime = lecService.getNextLectureDate(
				existingLectureData.getLectureWeek(), existingLectureData.getLectureStart());
		
		// 입력된 날짜로 새로운 강의 시간 계산
		Calendar newLectureCal = Calendar.getInstance();
		newLectureCal.setTime(lectureData.getIsTempDate());
		newLectureCal.set(Calendar.HOUR_OF_DAY, newLectureStart + LectureService.FIRST_CLASS_CRITERIA);
		if(newLectureCal.before(Calendar.getInstance())){
			throw new PageRedirectException("이미 지난 날짜는 지정하실 수 없습니다.");
		}
		
		// 새로운 강의 시간
		Date newLectureTime = newLectureCal.getTime();
					
		// DB에 공지 내용 등록(메세지 푸쉬X)
		postNotice(noticeData, false);
		// DB result 담을 변수
		int result = 1;	
		// 알람 내용
		String noticeTitle = "";
		String noticeContent = "";
		// 강의 시간이 변경된 경우
		if(noticeData.getNoticeType().equals("changeDate")) {
		
			if(existingLectureTime.compareTo(newLectureTime) == 0
					&& lectureData.getLecturePlace().equals(existingLectureData.getLecturePlace())){
				throw new PageRedirectException("기존 강의와 동일합니다.");
			}
			// 휴강 처리
			try {
			result = lecRepo.setLectureCancel(new CancelLecture(existingLectureTime, lectureData.getLectureTimeId()));
			} catch (DuplicateKeyException e){
				throw new WrongRequestException("이미 다음 강의가 휴강 처리된 날짜입니다.");
			}
			if(result != 1){
				throw new WrongRequestException();
			} 
			// 임시 강의 시간 등록
			result = lecRepo.setTempLectureTime(lectureData);
			if(result != 1){
				throw new WrongRequestException();
			} 
			
			// 메세지 설정
			noticeTitle = "강의의";
			boolean isTimeChanged = false;
			if(existingLectureTime.compareTo(newLectureTime) != 0){
				noticeTitle += " 시간";
				
				noticeContent += getDateStr(existingLectureTime);
				noticeContent += setNoticeContentByClass(noticeContent, existingLectureData);
				noticeContent += " -> ";
				noticeContent += getDateStr(newLectureTime);
				noticeContent += setNoticeContentByClass(noticeContent, lectureData);
				
				isTimeChanged = true;
			}
			if(!lectureData.getLecturePlace().equals(existingLectureData.getLecturePlace())){
				if(isTimeChanged) {
					noticeTitle += "과 장소가";
					noticeContent += " / ";
				} else {
					noticeTitle += " 장소가";
				}
				noticeContent += existingLectureData.getLecturePlace();
				noticeContent += " -> ";
				noticeContent += lectureData.getLecturePlace();
			}
			else {
				noticeTitle += "이";
			}
			noticeTitle += " 변경되었습니다.";
		}
		// 휴강된 경우
		else if(noticeData.getNoticeType().equals("cancelDate")) {
			Calendar existingLectureCal = Calendar.getInstance();
			existingLectureCal.setTime(existingLectureTime);
			
			int existingLectureWeek = existingLectureCal.get(Calendar.DAY_OF_WEEK);
			if(newLectureCal.get(Calendar.DAY_OF_WEEK) != existingLectureWeek){
				throw new WrongRequestException("강의가 없는 날짜입니다.\\n" + LectureService.weekList[existingLectureWeek-1] + "요일을 선택해주세요.");
			}
			// 기존 강의의 시작시간으로 설정(날짜는 유지)
			newLectureCal.set(Calendar.HOUR_OF_DAY, existingLectureData.getLectureStart() + LectureService.FIRST_CLASS_CRITERIA);
			newLectureTime = newLectureCal.getTime();
			// 휴강 처리
			try{
			result = lecRepo.setLectureCancel(new CancelLecture(newLectureTime, lectureData.getLectureTimeId()));
			} catch (DuplicateKeyException e){
				throw new WrongRequestException("이미 휴강 처리된 날짜입니다.");
			}
			if(result != 1){
				throw new WrongRequestException();
			} 	
			// 메세지 설정
			noticeTitle = "강의가 휴강처리 되었습니다.";
			noticeContent = setNoticeContentByClass(getDateStr(newLectureTime), lectureData);
		}
		// 보강인 경우
		else if(noticeData.getNoticeType().equals("addDate")) {
		
			if(existingLectureTime.compareTo(newLectureTime) == 0){
				throw new PageRedirectException("기존 강의와 동일합니다.");
			}

			// 해당 강의의 강의시간(보강포함) 중 겹치는 시간 리스트
			List<LectureTime> alreadyLectureTimeList = lecRepo.getAlreadyLectureTime(lectureData);
			String errorStr = "";
			for(LectureTime alreadyLectureTime : alreadyLectureTimeList) {
				errorStr += "\\n" + alreadyLectureTime.getLectureStart() + "교시~";
				errorStr += alreadyLectureTime.getLectureEnd() + "교시";
				
				Date tempDate = alreadyLectureTime.getIsTempDate();
				if(tempDate != null){
					errorStr += "(보강)";
				}
			}
			if(!errorStr.equals("")){
				throw new WrongRequestException(getDateStr(lectureData.getIsTempDate()) + "강의 시간이 중복됩니다." + errorStr);
			}
			
			// 임시 강의 시간 등록
			result = lecRepo.setTempLectureTime(lectureData);
			if(result != 1){
				throw new WrongRequestException();
			} 
			// 메세지 설정
			noticeTitle = "보강이 등록 되었습니다.";
			noticeContent = setNoticeContentByClass(getDateStr(newLectureTime), lectureData);
		}
		
		noticeData.setNoticeTitle(noticeTitle);
		noticeData.setNoticeContent(noticeContent);
		
		List<String> userIdList = gcmRepo.getLectureApplyUser(
				new Lecture(lectureData.getLectureId(), lectureData.getLectureClass()));
	
		// 메세지 PUSH
		new GCM(lectureName,
				noticeData.getNoticeTitle(), 
				noticeData.getNoticeContent(),
				userIdList,
				GCM.TYPE_SETTING_NOTICE);
		
		return true;
	}
	
	/** 알림 내용에 강의 시간을 추가 */
	@Override
	public String setNoticeContentByClass(String contentData, LectureTime timeData){
		contentData += LectureService.weekList[timeData.getLectureWeek()] + "요일 ";
		if(timeData.getLectureStart() != timeData.getLectureEnd()){
			contentData += timeData.getLectureStart() + "교시~";
		}
		contentData += timeData.getLectureEnd() + "교시";
		return contentData;
	}
	
	/** 날짜 출력 */
	private String getDateStr(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String dateStr = "";
		dateStr += (cal.get(Calendar.MONTH)+1) + "월";
		dateStr += cal.get(Calendar.DAY_OF_MONTH) + "일 ";
		return dateStr;
	}
	
	/** 공지내용을 알림으로 푸쉬 */
	private void sendGCM(LectureNotice noticeData){
		Integer lectureId = noticeData.getLectureId();
		Integer lectureClass = noticeData.getLectureClass();
		String lectureName = lecRepo.getLectureName(new Lecture(lectureId, lectureClass));
		
		List<String> userIdList = gcmRepo.getLectureApplyUser(new Lecture(lectureId, lectureClass));
	
		// 메세지 PUSH
		new GCM(lectureName,
				noticeData.getNoticeTitle(), 
				noticeData.getNoticeContent(),
				userIdList,
				GCM.TYPE_NOTICE);
	}
}
