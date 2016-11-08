package academy.group5.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;

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
}
