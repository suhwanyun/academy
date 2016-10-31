package academy.group5.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Manager;

@Repository
public class ManagerRepo {
	
	private final String MANAGER_NS = "academy.repo.ManagerMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public String getManager(Manager managerData) {
		String stmt = MANAGER_NS + "selectManager";
		return session.selectOne(stmt, managerData);
	}
}
