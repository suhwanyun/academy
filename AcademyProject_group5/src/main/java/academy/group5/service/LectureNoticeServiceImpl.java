package academy.group5.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		// 공지사항 정보, 새 강의 정보
		LectureNotice noticeData = new LectureNotice(lectureNoticeAndTime);
		LectureTime lectureData = new LectureTime(lectureNoticeAndTime);	
		
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
		newLectureCal.set(Calendar.HOUR_OF_DAY, lectureData.getLectureStart() + LectureService.FIRST_CLASS_CRITERIA);
		// 새로운 강의 시간
		Date newLectureTime = newLectureCal.getTime();
					
		// DB에 공지 내용 등록(메세지 푸쉬X)
		postNotice(noticeData, false);
		// DB result 담을 변수
		int result;	
		
		// 강의 시간이 변경된 경우
		if(noticeData.getNoticeType().equals("changeDate")) {
		
			if(existingLectureTime.compareTo(newLectureTime) == 0
					&& lectureData.getLecturePlace().equals(existingLectureData.getLecturePlace())){
				throw new PageRedirectException("기존 강의와 동일합니다.");
			}
			// 휴강 처리
			result = lecRepo.setLectureCancel(new CancelLecture(existingLectureTime, lectureData.getLectureTimeId()));
			if(result != 1){
				throw new WrongRequestException();
			} 
			// 임시 강의 시간 등록
			result = lecRepo.setTempLectureTime(lectureData);
			if(result != 1){
				throw new WrongRequestException();
			} 
			
			String lectureName = lecRepo.getLectureName(lectureData.getLectureId());
			// 메세지 설정
			String noticeTitle = lectureName + "의";
			String noticeContent = "";
			boolean isTimeChanged = false;
			if(existingLectureTime.compareTo(newLectureTime) != 0){
				noticeTitle += " 시간";
				
				noticeContent = LectureService.weekList[existingLectureData.getLectureWeek()] + "요일 ";
				noticeContent += existingLectureData.getLectureStart() + "교시~";
				noticeContent += existingLectureData.getLectureEnd() + "교시";
				
				noticeContent += " -> " + LectureService.weekList[lectureData.getLectureWeek()] + "요일 ";
				noticeContent += lectureData.getLectureStart() + "교시~";
				noticeContent += lectureData.getLectureEnd() + "교시";
				
				isTimeChanged = true;
			}
			if(!lectureData.getLecturePlace().equals(existingLectureData.getLecturePlace())){
				if(isTimeChanged) {
					noticeTitle += "과 장소가";
					noticeContent += " / ";
				} else {
					noticeTitle += " 장소가";
				}
				
				noticeContent += existingLectureData.getLecturePlace() + " -> ";
				noticeContent += lectureData.getLecturePlace();
			}
			else {
				noticeTitle += "이";
			}
			noticeTitle += " 변경되었습니다.";
			
			noticeData.setNoticeTitle(noticeTitle);
			noticeData.setNoticeContent(noticeContent);
		}
		// 휴강된 경우
		else if(noticeData.getNoticeType().equals("cancelDate")) {
			Calendar existingLectureCal = Calendar.getInstance();
			existingLectureCal.setTime(existingLectureTime);
			
			int existingLectureWeek = existingLectureCal.get(Calendar.DAY_OF_WEEK);
			if(newLectureCal.get(Calendar.DAY_OF_WEEK) != existingLectureWeek){
				throw new WrongRequestException("강의가 없는 날짜입니다.\\n" + LectureService.weekList[existingLectureWeek-1] + "요일을 선택해주세요.");
			}
			// 휴강 처리
			result = lecRepo.setLectureCancel(new CancelLecture(newLectureTime, lectureData.getLectureTimeId()));
			if(result != 1){
				throw new WrongRequestException();
			} 

			String lectureName = lecRepo.getLectureName(lectureData.getLectureId());	
			// 메세지 설정
			String noticeTitle = lectureName + "이(가) 휴강처리 되었습니다. (";
			noticeTitle += LectureService.weekList[lectureData.getLectureWeek()] + "요일 ";
			noticeTitle += lectureData.getLectureStart() + "교시~";
			noticeTitle += lectureData.getLectureEnd() + "교시)";
	
			noticeData.setNoticeTitle(noticeTitle);

		}
		
		List<String> userIdList = gcmRepo.getLectureApplyUser(
				new Lecture(lectureData.getLectureId(), lectureData.getLectureClass()));
	
		// 메세지 PUSH
		new GCM(noticeData.getNoticeTitle(), 
				noticeData.getNoticeContent(),
				userIdList,
				GCM.TYPE_NOTICE);
		
		return true;
	}
	
	/** 공지내용을 알림으로 푸쉬 */
	private void sendGCM(LectureNotice noticeData){
		Integer lectureId = noticeData.getLectureId();
		Integer lectureClass = noticeData.getLectureClass();
		
		List<String> userIdList = gcmRepo.getLectureApplyUser(new Lecture(lectureId, lectureClass));
	
		// 메세지 PUSH
		new GCM(noticeData.getNoticeTitle(), 
				noticeData.getNoticeContent(),
				userIdList,
				GCM.TYPE_NOTICE);
	}
}
