package academy.group5.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.UserData;

@Repository
public class LoginRepo {
	
	private final String LOGIN_NS = "academy.repo.UserDataMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public UserData getUser(String id) {
		String stmt = LOGIN_NS + "selectUser";
		return session.selectOne(stmt, id);
	}
	
	public int setUser(UserData data){
		String stmt = LOGIN_NS + "insertUser";
		return session.insert(stmt, data);
	}
}
