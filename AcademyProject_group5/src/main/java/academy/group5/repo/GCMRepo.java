package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Notifications;

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
}
