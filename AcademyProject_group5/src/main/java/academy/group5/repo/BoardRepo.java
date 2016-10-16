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
	
	/** 전체 글 목록 출력 */
	public List<Posting> getAllPosting(Paging page) {
		String stmt = BOARD_NS + "selectAllPosting";
		return session.selectList(stmt, page);
	}
	
	/** 
	 * 글 작성
	 * @param postingData (required = userId, postingType)
	 * @return
	 */
	public int setPosting(Posting postingData) {
		String stmt = BOARD_NS + "insertPosting";
		return session.insert(stmt, postingData);
	}
	
	/** 글 수정 */
	public int updateposting(Posting updateData) {
		String stmt = BOARD_NS + "updatePosting";
		return session.update(stmt, updateData);
	}
	
	/** 이미지 등록 */
	public int updatePhoto(Posting updateData) {
		String stmt = BOARD_NS + "updatePhoto";
		return session.update(stmt, updateData);
	}
	
	/** 게시글 번호 확인 */
	public Integer getPostingId(Posting selectData){
		String stmt = BOARD_NS + "selectPostingId";
		return session.selectOne(stmt, selectData);
	}
}
