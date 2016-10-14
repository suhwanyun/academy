package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Lecture;

@Repository
public class GCMRepo {
	
	private final String GCM_NS = "academy.repo.GCMMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	/** 전체 학생 */
	public List<String> getAllUser() {
		String stmt = GCM_NS + "allUser";
		return session.selectList(stmt);
	}
	
	/** 반장 */
	public String getPresident(int lectureId) {
		String stmt = GCM_NS + "president";
		return session.selectOne(stmt, lectureId);
	}
	
	/** 해당 강의를 듣는 모든 학생 */
	public List<String> getLectureApplyUser(Lecture data) {
		String stmt = GCM_NS + "lectureApplyUser";
		return session.selectList(stmt, data);
	}
	
	/** 특정 학생 */
	public String getOneUser(String userId) {
		String stmt = GCM_NS + "oneUser";
		return session.selectOne(stmt, userId);
	}
}
