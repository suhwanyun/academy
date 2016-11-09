package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.Paging;
import academy.group5.dto.etc.UserLectureTime;

@Repository
public class LectureRepo {
	
	private final String LECTURE_NS = "academy.repo.LectureMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public List<Lecture> getAllLecture(Paging data) {
		String stmt = LECTURE_NS + "selectAllLecture";
		return session.selectList(stmt, data);
	}
	
	public int isLectureApplied(LectureApply data){
		String stmt = LECTURE_NS + "selectIsLectureApply";
		return session.selectOne(stmt, data);
	}
	
	public int setLectureApply(LectureApply data){
		String stmt = LECTURE_NS + "insertLectureApply";
		return session.insert(stmt, data);
	}
	
	public int deleteLectureApply(LectureApply data){
		String stmt = LECTURE_NS + "deleteLectureApply";
		return session.delete(stmt, data);
	}
	
	public List<UserLectureTime> getUserLecture(String id) {
		String stmt = LECTURE_NS + "selectLectureByUser";
		return session.selectList(stmt, id);
	}
	
	public Lecture getLectureByClass(Lecture data) {
		String stmt = LECTURE_NS + "selectLectureByClass";
		return session.selectOne(stmt, data);
	}
	
	public List<UserLectureTime> getUserTimetable(String id){
		String stmt = LECTURE_NS + "selectLectureTimeByUser";
		return session.selectList(stmt, id);
	}
	
	public List<LectureTime> getLectureTime(Lecture data){
		String stmt = LECTURE_NS + "selectLectureTimeByLecture";
		return session.selectList(stmt, data);
	}
	
	public List<UserLectureTime> getAlreadyLectureTime(UserLectureTime userTimeData) {
		String stmt = LECTURE_NS + "selectAlreadyLectureTime";
		return session.selectList(stmt, userTimeData);
	}
	
	public LectureApply getIsPresident(LectureApply data){
		String stmt = LECTURE_NS + "selectIsPresident";
		return session.selectOne(stmt, data);
	}
	
	public List<Integer> getAllLectureId(){
		String stmt = LECTURE_NS + "selectLectureIdList";
		return session.selectList(stmt);
	}
}
