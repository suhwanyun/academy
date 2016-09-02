package workshop.view.client_backup;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import workshop.model.dao.BoardDao;
import workshop.model.dao.BoardDaoStub;
import workshop.model.dao.CustomerDao;
import workshop.model.dao.CustomerDaoStub;
import workshop.model.dao.ReplyDao;
import workshop.model.dao.ReplyDaoStub;
import workshop.model.dto.Customer;
import workshop.controller.service.BoardService;
import workshop.controller.service.BoardServiceStub;
import workshop.model.util.DBUtil;

public class TestCode {
	static Logger logger = LoggerFactory.getLogger(TestCode.class);
	static SqlSession session;
	static BoardDao bd ;
	static CustomerDao cd;
	static ReplyDao rd;
	static BoardService board_service = BoardServiceStub.getService();
	
	//클래스 시작 시 초기화.
	@BeforeClass
	public static void setUpBeforeClass(){
		System.out.println("BeforeClasses");
		session = DBUtil.getDBUtil().getSession();
		bd = BoardDaoStub.getBoard();
		cd = CustomerDaoStub.getCustomer();
		rd = ReplyDaoStub.getReply();
	}

	//Test 구동 전.
	@Before
	public void setUp(){
		System.out.println("Set up");
	}
	
	/*@Test
	public void test(){	
		System.out.println("Test 시작");
		List<Board> list = bd.selectAllBoard(session);
		assertThat(list, is(notNullValue()));
		Board rboard = bd.selectBoard(session, 10);
		assertThat(rboard, is(notNullValue()));
		
		//bd.insertBoard(session, new Board("raven924 넌 누구야 대체", "raven92"));
		bd.deleteBoard(session, 28);
		session.commit();
		
	}*/
	
	@Test
	public void testCustomer(){	
		System.out.println("Test 시작");
		List<Customer> list = cd.selectAllCustomer(session);
		assertThat(list, is(notNullValue()));
		Customer cboard = cd.selectCustomer(session, "ysh5516");
		assertThat(cboard, is(notNullValue()));
		
		cd.insertCustomer(session, new Customer("ysh5586", "홍길동", "kildong221"));
		cd.deleteCustomer(session, "ysh5586");
		session.commit();
	}
	
	@After
	public void tearDownAfter(){
		System.out.println("Test 완료");
	}
	
	@AfterClass
	public static void tearDwonAfterClass(){
		System.out.println("TEST 종료");
	}
	
	
}
