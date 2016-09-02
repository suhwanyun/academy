package workshop.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import workshop.model.dto.Reply;

public interface ReplyDao {
	
	/**
	 * 모든 댓글의 목록을 List에 담아서 리턴한다.
	 * @param con
	 * @return
	 */
	public List<Reply> selectAllReply(SqlSession session);
	
	/**
	 * 특정 번호의 댓글을 리턴한다.
	 * @param con
	 * @param no
	 * @return
	 */
	public Reply selectReply(SqlSession session, int no);
	
	/**
	 * 특정 번호의 댓글을 삭제한다.
	 * @param con
	 * @param no
	 */
	public int deleteReply(SqlSession session, int no);
	
	/**
	 * 전달받은 Reply 로 DB를 수정한다.
	 * @param con
	 * @param reply
	 */
	public int updateReply(SqlSession session, Reply reply);
	
	/**
	 * 전달받은 Reply를 DB에 등록한다.
	 * @param con
	 * @param reply
	 */
	public int insertReply(SqlSession session, Reply reply);
	
	/**
	 * 특정 boardNo에 등록된 댓글의 정보를 List에 담아서 리턴한다.
	 * @param con
	 * @param boardNo
	 * @return
	 */
	public List<Reply> selectReplyByBoardNo(SqlSession session, int boardNo);
	
	/**
	 * 특정 boardNo에 해당하는 댓글들을 삭제한다.
	 * @param con
	 * @param boardNo
	 */
	public int deleteReplyByBoardNo(SqlSession session, int boardNo);
}
