package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.LectureNotice;
import academy.group5.dto.etc.LecturePaging;
import academy.group5.dto.etc.UserLectureNotice;

@Repository
public class LectureNoticeRepo {
	
	private final String LOGIN_NS = "academy.repo.LectureNoticeMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public List<UserLectureNotice> getAllLectureNoticeList(LecturePaging data) {
		String stmt = LOGIN_NS + "selectAllLectureNoticeByUser";
		return session.selectList(stmt, data);
	}
	
	public List<LectureNotice> getLectureNoticeList(LecturePaging data) {
		String stmt = LOGIN_NS + "selectLectureNotice";
		return session.selectList(stmt, data);
	}
	
	public LectureNotice getLectureNoticeInfo(Integer lectureNoticeId) {
		String stmt = LOGIN_NS + "selectLectureNoticeInfo";
		return session.selectOne(stmt, lectureNoticeId);
	}
	
	public int setLectureNotice(LectureNotice data){
		String stmt = LOGIN_NS + "insertLectureNotice";
		return session.insert(stmt, data);
	}
}
