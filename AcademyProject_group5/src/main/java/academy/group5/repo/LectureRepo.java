package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.Paging;

@Repository
public class LectureRepo {
	
	private final String LECTURE_NS = "academy.repo.LectureMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public List<Lecture> getAllLecture(Paging data) {
		String stmt = LECTURE_NS + "selectLectureByPage";
		return session.selectList(stmt, data);
	}
	
	public int setLecture(LectureApply data){
		String stmt = LECTURE_NS + "insertLectureAllpy";
		return session.insert(stmt, data);
	}
	
	public int deleteLecture(LectureApply data){
		String stmt = LECTURE_NS + "deleteLectureApply";
		return session.delete(stmt, data);
	}
	
	public List<Lecture> getUserLecture(String id) {
		String stmt = LECTURE_NS + "selectLectureByUser";
		return session.selectList(stmt, id);
	}
	
	public List<LectureTime> getUserTimetable(String id){
		String stmt = LECTURE_NS + "selectLectureTimeByUser";
		return session.selectList(stmt, id);
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
