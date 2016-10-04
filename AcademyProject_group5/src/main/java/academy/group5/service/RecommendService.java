package academy.group5.service;

public interface RecommendService {
	/**
	 * 추천 서비스(1.등록이된지 확인하고 2.등록이 되지않았다면 등록하고)
	 * @param postingId
	 * @param postingType
	 * @param userId
	 * @return
	 */
	boolean recommend(Integer postingId, String postingType, String userId);
}
