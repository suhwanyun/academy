package academy.group5.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.CancelLecture;
import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.LectureNoticeSetTime;
import academy.group5.dto.etc.Paging;
import academy.group5.dto.etc.UserLectureTime;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.LectureNoticeRepo;
import academy.group5.repo.LectureRepo;
import academy.group5.repo.TermRepo;
import academy.group5.util.GCM;
import academy.group5.util.MyDate;

@Service
@Transactional
public class LectureServiceImpl implements LectureService{
	/** 한 페이지에 표시되는 강의의 수 */
	private final int LECTURE_MAX_PAGE = 10;

	@Autowired
	LectureRepo lecRepo;
	
	@Autowired
	LectureNoticeRepo notiRepo;
	
	@Autowired
	TermRepo termRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	MyDate dateUtil = new MyDate();
	
	@Override
	public List<Lecture> allLectureList(int page, String searchData, String searchType) {
		return lecRepo.getAllLecture(new Paging(page, LECTURE_MAX_PAGE, searchData, searchType));
	}

	private String[] weekList = {"일", "월", "화", "수", "목", "금", "토"};
	@Override
	public boolean apply(Integer lectureId, String userId, Integer lectureClass, String isPresident) {

		Lecture newLectureData = new Lecture(lectureId, lectureClass);
			
		if(isAppliedLecture(lectureId, userId, lectureClass)){
			throw new WrongRequestException("이미 신청한 강의입니다.");
		}
		
		// 현재 신청하려는 강의의 강의 시간 리스트
		List<LectureTime> selectedLectureTimeList = lecRepo.getLectureTime(newLectureData);
		
		if(selectedLectureTimeList.size() == 0){
			throw new WrongRequestException();
		}
		
		String errorStr = "";
		for(LectureTime selectedLectureTime : selectedLectureTimeList){
			UserLectureTime userLectureTimeData = new UserLectureTime(
					userId, 
					selectedLectureTime.getLectureStart(), 
					selectedLectureTime.getLectureEnd(), 
					selectedLectureTime.getLectureWeek());
			// 이미 신청한 강의들의 강의시간 중 겹치는 시간 리스트
			List<UserLectureTime> alreadyLectureTimeList = lecRepo.getAlreadyLectureTime(userLectureTimeData);
			
			for(UserLectureTime alreadyLectureTime : alreadyLectureTimeList) {
				errorStr += "\\n" + alreadyLectureTime.getLectureName() + " ";
				errorStr += alreadyLectureTime.getLectureClass() + "분반(";
				errorStr += weekList[alreadyLectureTime.getLectureWeek()-1] + "요일 ";
				errorStr += alreadyLectureTime.getLectureStart() + "교시~";
				errorStr += alreadyLectureTime.getLectureEnd() + "교시)";
			}
		}
		if(!errorStr.equals("")){
			throw new WrongRequestException("다음 강의와 시간표가 중복됩니다." + errorStr);
		}
		
		String isPresidentStr = isPresident.equals("true") ? "Y" : "N";
		int result = lecRepo.setLectureApply(new LectureApply(lectureId, userId, lectureClass, isPresidentStr, null));
		
		if(result != 1){
			throw new WrongRequestException();
		} 	
		return true;	
	}
	
	@Override
	public boolean isAppliedLecture(Integer lectureId, String userId, Integer lectureClass){
		int alreadyApplied = lecRepo.isLectureApplied(new LectureApply(lectureId, userId, lectureClass));
		return alreadyApplied != 0;
	}

	@Override
	public boolean cancelApply(Integer lectureId, String userId) {
		LectureApply data = new LectureApply();
		data.setLectureId(lectureId);
		data.setUserId(userId);
		
		int result = lecRepo.deleteLectureApply(data);
		
		if(result != 1){
			throw new WrongRequestException();
		} 	
		return true;
	}

	@Override
	public List<UserLectureTime> userLectureList(String userId) {
		List<UserLectureTime> lectureTimeList = lecRepo.getUserLecture(userId);

		for(UserLectureTime timeData : lectureTimeList){
			
			if(getIsPresident(timeData.getLectureId(), userId, timeData.getLectureClass())){
				timeData.setIsPresident("Y");
			} else {
				timeData.setIsPresident("N");
			}
		}
		return lectureTimeList;
	}
	
	@Override
	public Lecture lectureClassInfo(Integer lectureId, Integer lectureClass) {
		Lecture lectureData = lecRepo.getLectureByClass(new Lecture(lectureId, lectureClass));
		
		if(lectureData == null){
			throw new WrongRequestException();
		}
		return lectureData;
	}
	
	@Override
	public List<LectureTime> lectureTimeInfo(Lecture lecture) {
		
		List<LectureTime> lectureTImes = lecRepo.getLectureTime(lecture);
		
		if(lectureTImes == null || lectureTImes.size() == 0){
			throw new WrongRequestException();
		}	
		for(LectureTime time : lectureTImes){
			time.setLectureWeek(time.getLectureWeek());
		}	
		return lectureTImes;
	}
	

	@Override
	public List<UserLectureTime> timetable(String userId) {
		return lecRepo.getUserTimetable(userId);
	}

	@Override
	public boolean getIsPresident(Integer lectureId, String userId, Integer lectureClass) {
		LectureApply data = new LectureApply(lectureId, userId, lectureClass);
		LectureApply result = lecRepo.getIsPresident(data);	
		// 강의 신청 기록이 없음
		if(result == null){
			throw new WrongRequestException();
		}
		String president = result.getIsPresident();
		Date time = result.getRightEndTime();
		
		if(president == null){
			throw new WrongRequestException();
		}
		// 처음부터 반장이 아니거나, 임시 반장이었으나 기간이 지남	
		else if(president.equals("N") ||
				(president.equals("Y") && time != null && 
				 time.compareTo(dateUtil.getTodayEnd()) > 0)){
			return false;	
		}
		// 반장
		else{
			return true;
		}
	}
	
	@Override
	public boolean postNotice(LectureNotice noticeData) {
		
		Integer lectureId = noticeData.getLectureId();
		Integer lectureClass = noticeData.getLectureClass();
		
		int result = notiRepo.setLectureNotice(noticeData);
		if(result != 1){
			throw new WrongRequestException();
		} 
		
		List<String> userIdList = gcmRepo.getLectureApplyUser(new Lecture(lectureId, lectureClass));
	
		// 메세지 PUSH
		new GCM(noticeData.getNoticeTitle(), 
				noticeData.getNoticeContent(),
				userIdList,
				GCM.TYPE_NOTICE);
		
		return true;
	}
	
	@Override
	public boolean postNotice(LectureNoticeSetTime lectureNoticeAndTime) {
		/*
		int result = notiRepo.setLectureNotice(noticeData);
		if(result != 1){
			throw new WrongRequestException();
		} 
		
		// 강의 시간이 변경된 경우
		if(noticeData.getNoticeType().equals("changeDate")) {
			if(lectureData == null){
				throw new WrongRequestException();
			}
			// 기존 강의 정보
			LectureTime existingLectureData = lecRepo.getLectureTimeById(lectureData.getLectureTimeId());
			if(existingLectureData == null){
				throw new WrongRequestException();
			}
			// 휴강처리 될 기존 강의 시간
			Date cancelLectureTime = getNextLectureDate(existingLectureData.getLectureWeek(), existingLectureData.getLectureStart());
			// 임시 강의 시간
			Date tempLectureTime = 
					getNextLectureDate(lectureData.getLectureWeek(), lectureData.getLectureStart());
			lectureData.setIsTempDate(tempLectureTime);
			if(cancelLectureTime.compareTo(tempLectureTime) == 0
					&& lectureData.getLecturePlace().equals(existingLectureData.getLecturePlace())){
				throw new PageRedirectException("기존 강의와 동일합니다.");
			}
			
			// 휴강 처리
			result = lecRepo.setLectureCancel(new CancelLecture(cancelLectureTime, lectureData.getLectureTimeId()));
			if(result != 1){
				throw new WrongRequestException();
			} 
			// 임시 강의 시간 등록
			result = lecRepo.setTempLectureTime(lectureData);
			if(result != 1){
				throw new WrongRequestException();
			} 
			
			String lectureName = lecRepo.getLectureName(lectureId);
			
			// 메세지 설정
			String noticeTitle = lectureName + "의";
			String noticeContent = "";
			boolean isTimeChanged = false;
			if(cancelLectureTime.compareTo(tempLectureTime) != 0){
				noticeTitle += " 시간";
				
				noticeContent = weekList[existingLectureData.getLectureWeek()] + "요일 ";
				noticeContent += existingLectureData.getLectureStart() + "교시~";
				noticeContent += existingLectureData.getLectureEnd() + "교시";
				
				noticeContent += " -> " + weekList[lectureData.getLectureWeek()] + "요일 ";
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
		else if(noticeData.getNoticeType().equals("cancelDate") 
				&& lectureData != null) {
			
			// 휴강처리 될 강의 시간
			Date cancelLectureTime = getNextLectureDate(lectureData.getLectureWeek(), lectureData.getLectureStart());
			// 휴강 처리
			result = lecRepo.setLectureCancel(new CancelLecture(cancelLectureTime, lectureData.getLectureTimeId()));
			if(result != 1){
				throw new WrongRequestException();
			} 

			String lectureName = lecRepo.getLectureName(lectureId);	
			// 메세지 설정
			String noticeTitle = lectureName + "이(가) 휴강처리 되었습니다. (";
			noticeTitle += weekList[lectureData.getLectureWeek()] + "요일 ";
			noticeTitle += lectureData.getLectureStart() + "교시~";
			noticeTitle += lectureData.getLectureEnd() + "교시)";
	
			noticeData.setNoticeTitle(noticeTitle);

		}
		
		List<String> userIdList = gcmRepo.getLectureApplyUser(new Lecture(lectureId, lectureClass));
	
		// 메세지 PUSH
		new GCM(noticeData.getNoticeTitle(), 
				noticeData.getNoticeContent(),
				userIdList,
				GCM.TYPE_NOTICE);
		*/
		return true;
	}
	
	@Override
	public List<LectureTime> getNextLectureTime(Integer lectureId, Integer lectureClass) {
		List<LectureTime> timeList = lecRepo.getLectureTime(new Lecture(lectureId, lectureClass));
		List<LectureTime> nextTimeList = new ArrayList<>();
		
		Date termEnd = termRepo.getTermEndDate();
		if(termEnd == null){
			throw new WrongRequestException();
		}
		
		for(LectureTime timeData : timeList) {
			// 다음 강의 시간
			Date nextLectureTime = 
					getNextLectureDate(timeData.getLectureWeek(), timeData.getLectureStart());
			// 다음 강의 시간이 종강 이후이면
			if(nextLectureTime.after(termEnd)){
				continue;
			}
			// 다음 강의 시간 저장
			timeData.setIsTempDate(nextLectureTime);
			nextTimeList.add(timeData);
		}
		
		return nextTimeList;
	}

	@Override
	public List<String> getLectureTimeStrList(List<LectureTime> lectureTimeList) {
		List<String> selectStrList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(LectureTime timeData : lectureTimeList){
			cal.setTime(timeData.getIsTempDate());
			String str = "";
			str += cal.get(Calendar.MONTH) + "월";
			str += cal.get(Calendar.DAY_OF_MONTH) + "일 ";
			str += weekList[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일 ";
			str += timeData.getLectureStart() + "교시~";
			str += timeData.getLectureEnd() + "교시";
			selectStrList.add(str);
		}
		return selectStrList;
	}
	
	@Override
	public LectureTime getLectureTimeById(int lectureTimeId){
		LectureTime timeData = lecRepo.getLectureTimeById(lectureTimeId);
		if(timeData == null){
			throw new WrongRequestException();
		}
		return timeData;
	}
	
	// 강의 시간 계산 기준
	// 1교시 = 9시 = 8 + 1
	private final int FIRST_CLASS_CRITERIA = 8; 
	
	/** 돌아오는 다음 강의 날짜 계산 */
	private Date getNextLectureDate(int lectureWeek, int startTime){
		
		Calendar lectureCal = Calendar.getInstance();
		lectureCal.set(Calendar.DAY_OF_WEEK, lectureWeek);
		lectureCal.set(Calendar.HOUR_OF_DAY, startTime + FIRST_CLASS_CRITERIA);
		lectureCal.set(Calendar.MINUTE, 0);
		lectureCal.set(Calendar.SECOND, 0);
		
		// 이미 지났으면 다음주로 설정
		if(lectureCal.before(Calendar.getInstance())){
			lectureCal.add(Calendar.DAY_OF_MONTH, 7);
		}
		
		return lectureCal.getTime();
	}
}
