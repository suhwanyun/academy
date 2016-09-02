package workshop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import workshop.model.dto.Board;

public class BoardDaoStub implements BoardDao {

	static Logger logger = LoggerFactory.getLogger(BoardDaoStub.class);
	
	// Singleton
	private BoardDaoStub() {
	}

	private static BoardDaoStub board = new BoardDaoStub();

	public static BoardDaoStub getBoard() {
		return board;
	}

	private String BOARD_NS = "jdbc.dao.BoardMapper.";

	@Override
	public List<Board> selectAllBoard(SqlSession session) {
		String stmt = BOARD_NS + "selectAllBoard";
		return session.selectList(stmt);
	}

	@Override
	public Board selectBoard(SqlSession session, int no) {
		String stmt = BOARD_NS + "selectBoard";
		return session.selectOne(stmt, no);
	}

	@Override
	public int deleteBoard(SqlSession session, int no) {
		String stmt = BOARD_NS + "deleteBoard";
		int result = session.delete(stmt, no);
		logger.trace("삭제 완료 : {}", result);
		return result;
	}

	@Override
	public int updateBoard(SqlSession session, Board board) {
		String stmt = BOARD_NS + "updateBoard";
		int result = session.update(stmt, board);
		logger.trace("수정 완료 : {}", result);
		return result;
	}

	@Override
	public int insertBoard(SqlSession session, Board board) {
		int result = -1;
		String stmt = BOARD_NS + "insertBoard";
		result = session.insert(stmt, board);
		logger.trace("생성 완료 : {}", result);
		return result;
	}
	
	@Override
	public List<Board> getBoardByPage(SqlSession session, int page) {
		String stmt = BOARD_NS + "getBoardByPage";
		Map<String, Object> map = new HashMap<>();
		map.put("from", ((page-1)*BOARD_PER_PAGE+1));
		map.put("to", ((page-1)*BOARD_PER_PAGE)+BOARD_PER_PAGE);
		System.out.println(map);
		return session.selectList(stmt, map);
	}

}
