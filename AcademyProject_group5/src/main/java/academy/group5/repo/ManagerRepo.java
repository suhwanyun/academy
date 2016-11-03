package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.Manager;
import academy.group5.dto.etc.Paging;

@Repository
public class ManagerRepo {
	
	private final String MANAGER_NS = "academy.repo.ManagerMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public String getManager(Manager managerData) {
		String stmt = MANAGER_NS + "selectManager";
		return session.selectOne(stmt, managerData);
	}
	
	public int setLecture(Lecture lectureData) {
		String stmt = MANAGER_NS + "insertLecture";
		return session.insert(stmt, lectureData);
	}
	
	public List<Lecture> getAllLecture(Paging pagingData) {
		String stmt = MANAGER_NS + "selectAllLecture";
		return session.selectList(stmt, pagingData);
	}
	
	public Lecture getLecture(Lecture lectureData) {
		String stmt = MANAGER_NS + "selectLecture";
		return session.selectOne(stmt, lectureData);
	}
	
	public int updateLecture(Lecture lectureData) {
		String stmt = MANAGER_NS + "updateLecture";
		return session.update(stmt, lectureData);
	}
	
	public int deleteLecture(Lecture lectureData) {
		String stmt = MANAGER_NS + "deleteLecture";
		return session.delete(stmt, lectureData);
	}
	
	public List<LectureTime> getAllLectureTime(Lecture lectureData) {
		String stmt = MANAGER_NS + "selectAllLectureTime";
		return session.selectList(stmt, lectureData);
	}
	
	public int isAlreadyLectureTime(LectureTime timeData) {
		String stmt = MANAGER_NS + "selectAlreadyLectureTime";
		return session.selectOne(stmt, timeData);
	}
	
	public LectureTime getLectureTime(int lecturetimeId) {
		String stmt = MANAGER_NS + "selectLectureTime";
		return session.selectOne(stmt, lecturetimeId);
	}
	
	public int insertLectureTime(LectureTime timeData) {
		String stmt = MANAGER_NS + "insertLectureTime";
		return session.insert(stmt, timeData);
	}
	
	public int updateLectureTime(LectureTime timeData) {
		String stmt = MANAGER_NS + "updateLectureTime";
		return session.update(stmt, timeData);
	}
	
	public int deleteAllLectureTime(Lecture lectureData) {
		String stmt = MANAGER_NS + "deleteAllLectureTime";
		return session.delete(stmt, lectureData);
	}
	
	public int deleteLectureTime(Integer timeId) {
		String stmt = MANAGER_NS + "deleteLectureTime";
		return session.delete(stmt, timeId);
	}
}
