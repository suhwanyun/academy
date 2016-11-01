package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Lecture;
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
}
