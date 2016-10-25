package academy.group5.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.UserData;

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
	
	/** 게시글 추천받아 마일리지 회득 */
	public int updateMileageByRecommend(UserData userData) {
		String stmt = MILEAGE_NS + "recommendPosting";
		return session.update(stmt, userData);
	}
}
