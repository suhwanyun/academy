package workshop.controller.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import workshop.model.dao.BoardDao;
import workshop.model.dao.BoardDaoStub;
import workshop.model.dao.CustomerDao;
import workshop.model.dao.CustomerDaoStub;
import workshop.model.dao.ReplyDao;
import workshop.model.dao.ReplyDaoStub;
import workshop.model.dto.Board;
import workshop.model.dto.Customer;
import workshop.model.dto.Reply;
import workshop.controller.exception.ServiceFailException;
import workshop.model.util.DBUtil;

public class BoardServiceStub implements BoardService {
	static Logger logger = LoggerFactory.getLogger(BoardServiceStub.class);
	static SqlSession session;
	static BoardDao bd = BoardDaoStub.getBoard();
	static CustomerDao cd = CustomerDaoStub.getCustomer();
	static ReplyDao rd = ReplyDaoStub.getReply();
	
	//Singleton
	private BoardServiceStub(){}
	public static BoardServiceStub getService(){
		return service;
	}
	private static BoardServiceStub service = new BoardServiceStub();

	
	@Override
	public void deleteBoard(int boardNo) throws ServiceFailException {
		
		try {
			session = DBUtil.getDBUtil().getSession();
			//게시글 댓글삭제
			rd.deleteReplyByBoardNo(session, boardNo);
			//게시글 삭제
			bd.deleteBoard(session, boardNo);
			// 변경 내용 반영
			session.commit();			
		} catch (RuntimeException e) {
			logger.error("board 삭제 서비스 실패");
			//사용자 예외 발생
			throw new ServiceFailException("게시글 삭제에 실패하였습니다.");
		}
	}

	@Override
	public void join(String id, String name, String pass) throws ServiceFailException {
		try {
			session = DBUtil.getDBUtil().getSession();
			cd.insertCustomer(session, new Customer(id, name, pass));
			session.commit();
		} catch (RuntimeException e) {
			logger.error("회원가입 실패 (중복된id) ");
			//사용자 예외 발생
			throw new ServiceFailException("이미 존재하는 ID입니다.");
		}
	}

	@Override
	public Customer login(String id, String pass) throws ServiceFailException {
		Customer customer = null;
		boolean isLogin = false; //로그인 되었는가
		
		try{
			session = DBUtil.getDBUtil().getSession();
			List<Customer> customers = cd.selectAllCustomer(session);
			
			for(int i=0; i<customers.size(); i++){
				customer = customers.get(i);
				
				//입력 정보가 일치하면 true
				if(customer.getCustId().equals(id) &&
						customer.getCustPass().equals(pass)){
					isLogin = true;
					break;
				}
			}		
			//로그인 실패 시 예외발생
			if(!isLogin)
				throw new ServiceFailException("로그인 실패");
			session.commit();
		}catch(RuntimeException e){
			logger.error("회원가입 실패 (중복된id) ");
			//실패 시 롤백
		}
		
		return customer;
	}

	@Override
	public List<Board> getBoardByPage(int page) throws ServiceFailException {
		List<Board> list;
		
		try {
			session = DBUtil.getDBUtil().getSession();
			//페이지 불러오기
			list = bd.getBoardByPage(session, page);
			//실행 완료
		} catch (RuntimeException e) {
			logger.error("페이지 불러오기 실패 ", e);
			//사용자 예외 발생
			throw new ServiceFailException("게시판 페이지를 불러오는 데 실패했습니다.");
		}
		
		return list;
	}

	@Override
	public void writeBoard(String content, String custId) throws ServiceFailException {
		
		try {
			session = DBUtil.getDBUtil().getSession();
			//페이지 불러오기
			bd.insertBoard(session, new Board(content, custId));
			//실행 완료
			session.commit();
		} catch (RuntimeException e) {
			logger.error("글 쓰기 실패 ");
			//사용자 예외 발생
			throw new ServiceFailException("글 작성에 실패하였습니다.");
		}
	}

	@Override
	public void writeReply(String content, String custId, int boardNo) throws ServiceFailException {
		try {
			session = DBUtil.getDBUtil().getSession();
			//페이지 불러오기
			rd.insertReply(session, new Reply(content, custId, boardNo));
			//실행 완료
			session.commit();
		} catch (RuntimeException e) {
			//사용자 예외 발생
			throw new ServiceFailException("덧글 작성에 실패하였습니다.");
		}

	}

	@Override
	public Map<String, Object> getBoard(int boardNo) throws ServiceFailException {
		Map<String, Object> map = new HashMap<>();
		List<Reply> replys_object;
		Board board_object;
		try {
			session = DBUtil.getDBUtil().getSession();
			//페이지 불러오기
			
			board_object = bd.selectBoard(session, boardNo);
			map.put("board", board_object);
			replys_object = rd.selectReplyByBoardNo(session, boardNo);
			map.put("replys", replys_object);
			
			//실행 완료
			session.commit();
		} catch (RuntimeException e) {
			logger.error("게시글, 덧글 불러오기 실패 ");
			//사용자 예외 발생
			throw new ServiceFailException("게시글, 덧글 불러오기에 실패하였습니다.");
		}
		
		return map;
	}

	@Override
	public void deleteReply(int replyNo) throws ServiceFailException {
		try {
			session = DBUtil.getDBUtil().getSession();
			//페이지 불러오기
			rd.deleteReply(session, replyNo);
			//실행 완료
			session.commit();
		} catch (RuntimeException e) {
			logger.error("덧글 삭제 실패 ");
			//사용자 예외 발생
			throw new ServiceFailException("덧글 삭제에 실패하였습니다.");
		}	

	}

}
