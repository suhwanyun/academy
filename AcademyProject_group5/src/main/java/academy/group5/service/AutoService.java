package academy.group5.service;

public interface AutoService {
	/**
	 * 반장선거
	 */
	void startVoteScheduler();
	/**
	 * 학기종료
	 */
	void startTermScheduler();
	/**
	 * 추천 게시글 마일리지 증가
	 */
	void startRecommendMileageScheduler();
}
