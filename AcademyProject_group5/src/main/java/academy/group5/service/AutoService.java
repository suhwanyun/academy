package academy.group5.service;

import academy.group5.dto.Term;

public interface AutoService {
	/**
	 * 반장선거
	 * @param term
	 */
	void voteForPresident(Term term);
	/**
	 * 학기종료
	 * @param term
	 */
	void endTerm(Term term);
}
