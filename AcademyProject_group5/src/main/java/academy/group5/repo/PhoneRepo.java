package academy.group5.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.UserData;

@Repository
public class PhoneRepo {
	
	private final String PHONE_NS = "academy.repo.PhoneMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	/** GCM 등록 */
	public int setGCMData(UserData userData) {
		String stmt = PHONE_NS + "gcmUpdate";
		return session.update(stmt, userData);
	}
	
}
