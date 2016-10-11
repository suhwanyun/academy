package academy.group5.service;

import academy.group5.dto.Term;

public interface AutoService {
	/**
	 * 반장선거
	 */
	void startVoteScheduler();
	/**
	 * 학기종료
	 */
	void startTermScheduler();
}
