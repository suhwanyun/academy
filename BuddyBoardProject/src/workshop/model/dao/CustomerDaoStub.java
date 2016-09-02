package workshop.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import workshop.model.dto.Customer;

public class CustomerDaoStub implements CustomerDao {	
	static Logger logger = LoggerFactory.getLogger(CustomerDaoStub.class);
	
	//Singleton
	private CustomerDaoStub() {}
	private static CustomerDaoStub customer = new CustomerDaoStub();
	
	public static CustomerDaoStub getCustomer(){
		return customer;
	}
	
	private String CUSTOMER_NS = "jdbc.dao.CustomerMapper.";
	
	@Override
	public List<Customer> selectAllCustomer(SqlSession session) {
		String stmt = CUSTOMER_NS + "selectAllCustomer";
		return session.selectList(stmt);
	}

	@Override
	public Customer selectCustomer(SqlSession session, String id){
		String stmt = CUSTOMER_NS + "selectCustomer";
		return session.selectOne(stmt, id);
	}

	@Override
	public int deleteCustomer(SqlSession session, String id) {
		String stmt = CUSTOMER_NS + "deleteCustomer";
		int result = session.delete(stmt, id);
		logger.trace("삭제 완료 : {}", result);
		return result;
	}

	@Override
	public int updateCustomer(SqlSession session, Customer customer) {
		String stmt = CUSTOMER_NS + "updateCustomer";
		int result = session.update(stmt, customer);
		logger.trace("수정 완료 : {}", result);
		return result;
	}

	@Override
	public int insertCustomer(SqlSession session, Customer customer) {
		String stmt = CUSTOMER_NS + "insertCustomer"; 
		int result = session.insert(stmt, customer);
		logger.trace("생성 완료 : {}", result);
		return result;
	}

}
