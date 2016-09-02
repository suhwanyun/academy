package workshop.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import workshop.model.dto.Reply;

public class ReplyDaoStub implements ReplyDao {
	static Logger logger = LoggerFactory.getLogger(ReplyDaoStub.class);
	
	//Singleton
	private ReplyDaoStub(){}
	private static ReplyDaoStub reply = new ReplyDaoStub();
	public static ReplyDaoStub getReply(){
		return reply;
	}
	
	private String REPLY_NS = "jdbc.dao.ReplyMapper.";
	
	@Override
	public List<Reply> selectAllReply(SqlSession session){
		String stmt = REPLY_NS + "selectAllReply";
		return session.selectList(stmt);
	}

	@Override
	public Reply selectReply(SqlSession session, int no){
		String stmt = REPLY_NS + "selectReply";
		return session.selectOne(stmt, no);
	}

	@Override
	public int deleteReply(SqlSession session, int no){
		String stmt = REPLY_NS + "deleteReply";
		int result = session.delete(stmt, no);
		logger.trace("삭제 완료 : {}", result);
		return result;
	}

	@Override
	public int updateReply(SqlSession session, Reply reply){	
		String stmt = REPLY_NS + "updateReply";
		int result = session.update(stmt, reply);
		logger.trace("수정 완료 : {}", result);
		return result;
	}

	@Override
	public int insertReply(SqlSession session, Reply reply){
		String stmt = REPLY_NS + "insertReply";
		int result = session.insert(stmt, reply);
		logger.trace("생성 완료 : {}", result);
		return result;
	}

	@Override
	public List<Reply> selectReplyByBoardNo(SqlSession session, int boardNo){
		String stmt = REPLY_NS + "selectReplyByBoardNo";
		System.out.println(stmt);
		return session.selectList(stmt, boardNo);
	}

	@Override
	public int deleteReplyByBoardNo(SqlSession session, int boardNo){
		String stmt = REPLY_NS + "deleteReplyByBoardNo";
		int result = session.delete(stmt, boardNo);
		return result;
	}

}
