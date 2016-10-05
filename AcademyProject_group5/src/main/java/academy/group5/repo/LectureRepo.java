package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Lecture;
import academy.group5.dto.etc.Paging;

@Repository
public class LectureRepo {
	
	private final String LECTURE_NS = "academy.repo.LectureMapper.";
	private final int LECTURE_MAX_PAGE = 10;
	
	@Autowired
	SqlSessionTemplate session;
	
	public List<Lecture> getAllLecture(int page) {
		String stmt = LECTURE_NS + "selectLecture";
		return session.selectList(stmt, new Paging(page, LECTURE_MAX_PAGE));
	}
	
	public int setLecture(Lecture data){
		String stmt = LECTURE_NS + "insertLecture";
		return session.insert(stmt, data);
	}
	
	
}
