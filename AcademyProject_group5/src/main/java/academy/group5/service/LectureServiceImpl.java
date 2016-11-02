package academy.group5.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.Paging;
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

	@Override
	public boolean apply(Integer lectureId, Integer lectureClass, String userId) {
		int result = lecRepo.setLecture(new LectureApply(lectureId, userId, lectureClass, "N", null));
		
		if(result != 1){
			throw new WrongRequestException();
		} 	
		return true;	
	}

	@Override
	public boolean delete(Integer lectureId, String userId) {
		LectureApply data = new LectureApply();
		data.setLectureId(lectureId);
		data.setUserId(userId);
		
		int result = lecRepo.deleteLecture(data);
		
		if(result != 1){
			throw new WrongRequestException();
		} 	
		return true;
	}

	@Override
	public List<Lecture> userLectureList(String userId) {
		return lecRepo.getUserLecture(userId);
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
	public List<LectureTime> timetable(String userId) {
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

}
