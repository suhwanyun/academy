package academy.group5.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.Paging;
import academy.group5.dto.etc.UserLectureTime;
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
			List<UserLectureTime> alreadyLectureTimeList = lecRepo.getAlreadyLectureTimeByUser(userLectureTimeData);
			
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
		// 알람 설정
		String phoneId = gcmRepo.getOneUser(userId);
		if(phoneId != null){
			List<String> userIdList = new ArrayList<>();
			userIdList.add(phoneId);
			new GCM(null, null, userIdList, GCM.TYPE_SETTING);
		}
		return true;	
	}
	
	@Override
	public boolean isTermStarted(){
		return termRepo.getTodayTerm() != null;	
	}
	
	@Override
	public boolean isAppliedLecture(Integer lectureId, String userId, Integer lectureClass){
		int alreadyApplied = lecRepo.isLectureApplied(new LectureApply(lectureId, userId, lectureClass));
		return alreadyApplied != 0;
	}

	@Override
	public boolean cancelApply(Integer lectureId, String userId) {
		LectureApply data = new LectureApply(lectureId, userId);
		
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
			
			if(isTermStarted() && getIsPresident(timeData.getLectureId(), userId, timeData.getLectureClass())){
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

		return isPresidentLogic(result);
	}
	@Override
	public boolean getIsPresident(Integer lectureTimeId, String userId){
		Lecture lectureData = lecRepo.getLectureByTimeId(lectureTimeId);
		LectureApply result = lecRepo.getIsPresident(new LectureApply(lectureData, userId));	
		
		return isPresidentLogic(result);
	}
	
	/** 반장여부 확인 로직 */
	private boolean isPresidentLogic(LectureApply applyData){
		// 강의 신청 기록이 없음
		if(applyData == null){
			throw new WrongRequestException();
		}
		String president = applyData.getIsPresident();
		Date time = applyData.getRightEndTime();
		
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
	public String getLectureTimeStr(LectureTime timeData) {

		Integer lecWeek = timeData.getLectureWeek();
		Integer lecStart = timeData.getLectureStart();
		if(lecWeek == null || lecStart == null){
			throw new WrongRequestException();
		}
		
		Calendar calInst = Calendar.getInstance();
		Date lectureDate = getNextLectureDate(lecWeek, lecStart);
		calInst.setTime(lectureDate);
		
		String lectureTimeStr = "";
		lectureTimeStr += (calInst.get(Calendar.MONTH) + 1) + "월";
		lectureTimeStr += calInst.get(Calendar.DAY_OF_MONTH) + "일 ";
		lectureTimeStr += weekList[calInst.get(Calendar.DAY_OF_WEEK) - 1] + "요일 ";
		lectureTimeStr += timeData.getLectureStart() + "교시~";
		lectureTimeStr += timeData.getLectureEnd() + "교시 / ";
		lectureTimeStr += timeData.getLecturePlace();
		
		return lectureTimeStr;
	}
	
	@Override
	public LectureTime getLectureTimeById(int lectureTimeId){
		LectureTime timeData = lecRepo.getLectureTimeById(lectureTimeId);
		if(timeData == null){
			throw new WrongRequestException();
		}
		return timeData;
	}
	
	@Override
	public Date getNextLectureDate(int lectureWeek, int startTime){
		
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
	
	@Override
	public String getLectureName(int lectureId, int lectureClass){
		String lecName = lecRepo.getLectureName(new Lecture(lectureId, lectureClass));
		if(lecName == null){
			throw new WrongRequestException();
		}
		return lecName;
	}
}
