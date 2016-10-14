package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Posting;
import academy.group5.dto.etc.Paging;

@Repository
public class BoardRepo {
	
	private final String BOARD_NS = "academy.repo.BoardMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	/** 전체 학생 */
	public List<Posting> getAllUser(Paging data) {
		String stmt = BOARD_NS + "selectAllPosting";
		return session.selectList(stmt, data);
	}
}
