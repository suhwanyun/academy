package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Posting;
import academy.group5.dto.PostingComment;
import academy.group5.dto.Recommend;
import academy.group5.dto.etc.MostRecommend;
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
	
	/** 가장 추천을 많이 받은 글 탐색 */
	public Posting getMostRecommendPosting(MostRecommend searchData) {
		String stmt = BOARD_NS + "selectMostRecommendPosting";
		return session.selectOne(stmt, searchData);
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
	
	/** 게시글 삭제 */
	public Integer delPosting(Posting delData){
		String stmt = BOARD_NS + "deletePosting";
		return session.delete(stmt, delData);
	}
	
	/** 게시글 정보 */
	public Posting getPostingInfo(Posting selectData){
		String stmt = BOARD_NS + "selectPostingInfo";
		return session.selectOne(stmt, selectData);
	}
	
	/** 댓글 확인(부모 댓글) */
	public List<PostingComment> getAllParentComment(Posting postingData){
		String stmt = BOARD_NS + "selectAllParentComment";
		return session.selectList(stmt, postingData);
	}
	
	/** 댓글 확인(자식 댓글) */
	public List<PostingComment> getAllChildComment(Posting postingData){
		String stmt = BOARD_NS + "selectAllChildComment";
		return session.selectList(stmt, postingData);
	}
	
	/** 댓글 등록 */
	public int setComment(PostingComment commentData) {
		String stmt = BOARD_NS + "insertComment";
		return session.insert(stmt, commentData);
	}

	/** 모든 댓글 삭제 */
	public Integer delAllComment(Posting delData){
		String stmt = BOARD_NS + "deleteAllComment";
		return session.delete(stmt, delData);
	}
	
	/** 자식이 있는 부모 댓글인지 확인 */
	public int isParentComment(int commentId) {
		String stmt = BOARD_NS + "isParentComment";
		return session.selectOne(stmt, commentId);
	}
	
	/** 실존하는(수정가능한) 댓글인지 확인 */
	public int isAliveComment(int commentId) {
		String stmt = BOARD_NS + "isAliveComment";
		return session.selectOne(stmt, commentId);
	}
	
	/** 댓글 삭제(부모가 존재하여 삭제 불가) */
	public Integer delCommentSetDefault(PostingComment commentData){
		String stmt = BOARD_NS + "deleteCommentSetDefault";
		return session.update(stmt, commentData);
	}
	
	/** 댓글 삭제 */
	public Integer delComment(int commentId){
		String stmt = BOARD_NS + "deleteComment";
		return session.delete(stmt, commentId);
	}
	
	/** 댓글 수정 */
	public int updateComment(PostingComment updateData) {
		String stmt = BOARD_NS + "updateComment";
		return session.update(stmt, updateData);
	}
	
	/** 추천 수 확인 */
	public int getRecommendsCount(Recommend recommendData) {
		String stmt = BOARD_NS + "selectRecommendsCount";
		return session.selectOne(stmt, recommendData);
	}
	
	/** 추천 여부 확인 */
	public int getRecommend(Recommend recommendData) {
		String stmt = BOARD_NS + "selectRecommend";
		return session.selectOne(stmt, recommendData);
	}

	/** 추천(회원 기준) */
	public int setRecommendUser(Recommend recommendData) {
		String stmt = BOARD_NS + "insertRecommendForUser";
		return session.insert(stmt, recommendData);
	}
	
	/** 추천(게시글 기준) */
	public int setRecommendPosting(int recommendCount) {
		String stmt = BOARD_NS + "insertRecommendForPosting";
		return session.insert(stmt, recommendCount);
	}
}
