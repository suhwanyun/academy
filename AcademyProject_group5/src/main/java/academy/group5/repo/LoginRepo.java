package academy.group5.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.UserData;
import academy.group5.dto.etc.UserId;
import academy.group5.dto.etc.UserPass;

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
	
	public int updateUser(UserData data){
		String stmt = LOGIN_NS + "updateUser";
		return session.update(stmt, data);
	}
	
	public int deleteUser(String data){
		String stmt = LOGIN_NS + "deleteUser";
		return session.delete(stmt, data);
	}
	
	public String getUserId(UserId data){
		String stmt = LOGIN_NS + "selectUserId";
		return session.selectOne(stmt, data);
	}
	
	public String getQuestion(String id) {
		String stmt = LOGIN_NS + "selectPassQuestion";
		return session.selectOne(stmt, id);
	}
	
	public String getPass(UserPass data) {
		String stmt = LOGIN_NS + "selectPass";
		return session.selectOne(stmt, data);
	}
}
