package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.MileageProduct;
import academy.group5.dto.UserData;
import academy.group5.dto.UserMileage;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.dto.etc.UserMileageProduct;

@Repository
public class MileageRepo {
	
	private final String MILEAGE_NS = "academy.repo.MileageMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	/** 마일리지 점수 확인 */
	public Integer getMileage(String userId) {
		String stmt = MILEAGE_NS + "selectMileage";
		return session.selectOne(stmt, userId);
	}
	
	/** 마일리지 설정 */
	public int setMileage(UserData userData) {
		String stmt = MILEAGE_NS + "updateMileage";
		return session.update(stmt, userData);
	}
	
	/** 최고 추천수 게시글 작성자 확인 */
	public String getMostRecommendPostingUserId(MostRecommend recommendData) {
		String stmt = MILEAGE_NS + "selectMostRecommendPosting";
		return session.selectOne(stmt, recommendData);
	}
	
	
	/** 모든 마일리지 물품 정보 획득 */
	public List<MileageProduct> getAllProduct() {
		String stmt = MILEAGE_NS + "selectAllProduct";
		return session.selectList(stmt);
	}
	
	/** 회원의 모든 마일리지 물품 정보 획득 */
	public List<MileageProduct> getAllProductByUser(String userId) {
		String stmt = MILEAGE_NS + "selectAllProductByUser";
		return session.selectList(stmt, userId);
	}
	
	/** 단일 마일리지 물품 정보 획득 */
	public MileageProduct getProduct(int productId) {
		String stmt = MILEAGE_NS + "selectProduct";
		return session.selectOne(stmt, productId);
	}
	
	/** 단일 마일리지 물품 정보 획득 */
	public UserMileageProduct getProductByUser(UserMileage userData) {
		String stmt = MILEAGE_NS + "selectProductByUser";
		return session.selectOne(stmt, userData);
	}
	
	/** 마일리지 물품의 남은 수량 정보 획득 */
	public Integer getProductRemain(UserMileage userData) {
		String stmt = MILEAGE_NS + "selectProductCountByUser";
		return session.selectOne(stmt, userData);
	}
	
	/** 마일리지 물품 구매 설정 */
	public int setProduct(UserMileage userData) {
		String stmt = MILEAGE_NS + "insertProduct";
		return session.insert(stmt, userData);
	}
	
	/** 보유한 물품 수량 변경 */
	public int updateProduct(UserMileage userData) {
		String stmt = MILEAGE_NS + "updateProduct";
		return session.update(stmt, userData);
	}
	
	/** 구매 기록 삭제 */
	public int delProduct(UserMileage userData) {
		String stmt = MILEAGE_NS + "deleteProduct";
		return session.delete(stmt, userData);
	}
}
