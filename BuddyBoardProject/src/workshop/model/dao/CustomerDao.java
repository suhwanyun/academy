package workshop.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import workshop.model.dto.Customer;

public interface CustomerDao {
	/**
	 * 모든 Customer의 정보를 List에 담아서 리턴한다.
	 * @param con
	 * @return
	 */
	public List<Customer> selectAllCustomer(SqlSession session);
	
	/**
	 * 지정된 ID를 사용하는 Customer를 조회 후 리턴한다.
	 * @param con
	 * @param id
	 * @return
	 */
	public Customer selectCustomer(SqlSession session, String id);
	
	/**
	 * 지정된 ID를 사용하는 고객의 정보를 삭제한다.
	 * 이때 고객이 작성한 게시글, 댓글에 대한 정보도 함께 삭제되어야 한다.
	 * @param con
	 * @param id
	 */
	public int deleteCustomer(SqlSession session, String id);
	
	/**
	 * 전달받은 Customer 객체로 고객 정보를 수정한다.
	 * @param con
	 * @param customer
	 */
	public int updateCustomer(SqlSession session, Customer customer);
	
	/**
	 * 전달받은 Customer 객체로 고객 정보를 등록한다.
	 * @param con
	 * @param customer
	 */
	public int insertCustomer(SqlSession session, Customer customer);
}
