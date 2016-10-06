package academy.group5.service;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Lecture> userLectureList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LectureTime> timetable(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getIsPresident(Integer lectureId, String userId, Integer lectureClass) {
		// TODO Auto-generated method stub
		return false;
	}

}
