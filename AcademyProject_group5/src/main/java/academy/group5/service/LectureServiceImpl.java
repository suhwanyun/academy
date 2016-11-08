package academy.group5.service;

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
import academy.group5.repo.LectureRepo;

@Service
@Transactional
public class LectureServiceImpl implements LectureService{
	/** 한 페이지에 표시되는 강의의 수 */
	private final int LECTURE_MAX_PAGE = 10;

	@Autowired
	LectureRepo lecRepo;
	
	@Override
	public List<Lecture> allLectureList(int page, String searchData, String searchType) {
		return lecRepo.getAllLecture(new Paging(page, LECTURE_MAX_PAGE, searchData, searchType));
	}

	private String[] weekList = {"일", "월", "화", "수", "목", "금", "토"};
	@Override
	public boolean apply(Integer lectureId, String userId, Integer lectureClass, String isPresident) {

		// 현재 신청하려는 강의의 강의 시간 리스트
		List<LectureTime> selectedLectureTimeList = lecRepo.getLectureTime(new Lecture(lectureId, lectureClass));
		
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
		// 다음날 0시 기준
		Date nextMidnight = getNextMidnight();
		List<UserLectureTime> lectureTimeList = lecRepo.getUserLecture(userId);
		
		for(UserLectureTime timeData : lectureTimeList){
			Date rightEnd = timeData.getRightEndTime();
			// 임시 반장 기간 확인
			if(rightEnd != null
					&& rightEnd.before(nextMidnight)){
				timeData.setIsPresident("Y");
			} else if(rightEnd != null){
				timeData.setIsPresident("N");
			}
		}
		return lectureTimeList;
	}
	
	@Override
	public Lecture lectureClassInfo(Integer lectureId, Integer lectureClass) {
		Lecture lectureData = lecRepo.getLectureByClass(new LectureTime(lectureId, lectureClass));
		
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
		LectureApply data = new LectureApply(lectureId, userId, lectureClass, null, null);
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
		// 처음부터 반장이 아님
		else if(president.equals("N")){
			return false;
		// (임시)반장
		} else if(time != null && time.compareTo(new Date()) <= 0){
			return true;
		// (임시)반장이었으나 기간이 지남
		} else{
			return false;
		}
	}
	
	private Date getNextMidnight(){
		Calendar calInst = Calendar.getInstance();
		calInst.add(Calendar.DAY_OF_MONTH, 1);
		calInst.set(Calendar.HOUR_OF_DAY, 0);
		calInst.set(Calendar.MINUTE, 0);
		calInst.set(Calendar.SECOND, 0);
		
		return calInst.getTime();
	}

}
