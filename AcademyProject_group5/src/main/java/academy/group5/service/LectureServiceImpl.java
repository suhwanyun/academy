package academy.group5.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureTime;
import academy.group5.repo.LectureRepo;

@Service
@Transactional
public class LectureServiceImpl implements LectureService{

	@Autowired
	LectureRepo lecRepo;
	
	// 전체 강의 목록
	@Override
	public List<Lecture> allLectureList(int page) {
		return lecRepo.getAllLecture(page);
	}

	// 강의 신청
	@Override
	public boolean apply(Integer lectureId, Integer lectureClass, String userId) {
		lecRepo.setLecture(new LectureApply(lectureId, userId, lectureClass, "0", null));
		return false;
	}

	@Override
	public boolean delete(Integer lectureId, String userId) {
		LectureApply data = new LectureApply();
		data.setLectureId(lectureId);
		data.setUserId(userId);
		
		lecRepo.deleteLecture(data);
		return false;
	}

	@Override
	public List<Lecture> userLectureList(String userId) {
		return lecRepo.getUserLecture(userId);
	}

	@Override
	public List<LectureTime> timetable(String userId) {
		return lecRepo.getUserTimetable(userId);
	}

	@Override
	public boolean getIsPresident(Integer lectureId, String userId, Integer lectureClass) {
		LectureApply data = new LectureApply(lectureId, userId, lectureClass, null, null);
		LectureApply result = lecRepo.getIsPresident(data);
		
		String president = result.getIsPresident();
		Date time = result.getRightEndTime();
		
		// 처음부터 반장이 아님
		if(president.equals("0")){
			return false;
		// (임시)반장
		} else if(time.compareTo(new Date()) <= 0){
			return true;
		// (임시)반장이었으나 기간이 지남
		} else{
			return false;
		}
	}

}
