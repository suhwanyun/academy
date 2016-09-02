package workshop.controller.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import workshop.model.dto.Board;
import workshop.model.dto.Customer;
import workshop.controller.exception.ServiceFailException;

/**
 * 클라이언트가 호출하는 서비스에 대한 인터페이스
 * DAO호출에서 발생하는 모든 예외는 ServiceFailException으로 감싸서 클라이언트에게 전달한다.
 * try {
		// do something
		} catch (SQLException e) {
			throw new ServiceFailException(e);
		}
 * 
 * @author andy
 *
 */
public interface BoardService {
	/**
	 * 받은 boardNo에 해당하는 데이터를 삭제한다.
	 * 지우려는 데이터가 없을 경우 ServiceFailException을 발생시킨다.
	 * @param boardNo
	 * @throws ServiceFailException
	 */
	void deleteBoard(int boardNo) throws ServiceFailException;

	/**
	 * 주어진 정보를 이용해 가입 처리한다.
	 * 사용하려는 id가 이미 저장된 값일 경우 ServiceFailexception을 발생시킨다.
	 * @param id
	 * @param name
	 * @param pass
	 * @throws ServiceFailException
	 */
	void join(String id, String name, String pass) throws ServiceFailException;

	/**
	 * 주어진 정보를 이용해 로그인 처리한다.
	 * 사용하려는 id로 로그인할 수 없을 경우 ServiceFailException을 발생시킨다.
	 * 로그인 결과로 사용자 객체를 리턴한다.
	 * @param id
	 * @param pass
	 * @return
	 * @throws ServiceFailException
	 */
	Customer login(String id, String pass) throws ServiceFailException;
	
	/**
	 * 주어진 page에 해당하는 게시글을 리턴한다.
	 * 한 페이지에 출력할 수 있는 게시글의 개수는 BoardDao 클래스에 정의되어있다.
	 * 조회된 Board를 List에 담아서 리턴한다.
	 * @param page
	 * @return
	 * @throws ServiceFailException
	 */
	List<Board> getBoardByPage(int page) throws ServiceFailException;
	
	/**
	 * 주어진 내용으로 게시글을 작성한다.
	 * @param content
	 * @param custId
	 * @throws ServiceFailException
	 */
	void writeBoard(String content, String custId) throws ServiceFailException;
	
	/**
	 * boardNo에 content와 custId를 이용해서 댓글을 등록한다.
	 * @param content
	 * @param custId
	 * @param boardNo
	 * @throws ServiceFailException
	 */
	void writeReply(String content, String custId, int boardNo) throws ServiceFailException;
	
	/**
	 * boardNo에 해당하는 board와 게시글 정보를 리턴한다.
	 * 예를들어 map.put("board", board_object); map.put("replys", List<Reply> );의 형태
	 * @param boardNo
	 * @return
	 * @throws ServiceFailException
	 */
	Map<String, Object> getBoard(int boardNo) throws ServiceFailException;
	/**
	 * 전달받은 replyNo에 해당하는 댓글을 삭제한다.
	 * @param replyNo
	 * @throws ServiceFailException
	 */
	void deleteReply(int replyNo) throws ServiceFailException;
}
