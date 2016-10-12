package academy.group5.repo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Term;
import academy.group5.dto.etc.Voting;

@Repository
public class TermRepo {
	
	private static final Logger logger = LoggerFactory.getLogger(TermRepo.class);
	
	
	private final String TERM_NS = "academy.repo.TermMapper.";
	
	private SimpleDateFormat transFormat = new SimpleDateFormat("YYYY-mm-dd");
	
	@Autowired
	SqlSessionTemplate session;
	
	public Term getTermByTerm(Term data) {
		String stmt = TERM_NS + "selectTermByTerm";
		return session.selectOne(stmt, data);
	}
	
	public Term getTodayTerm() {
		String stmt = TERM_NS + "selectTodayTerm";
		return session.selectOne(stmt);
	}
	
	public List<Term> getTermByDate(Term data) {
		String stmt = TERM_NS + "selectTermByDate";
		return session.selectList(stmt, data);
	}
	
	public int setTerm(Term data) {
		String stmt = TERM_NS + "insertTerm";
		return session.insert(stmt, data);
	}
	
	public Date getNextTermStartDate() {
		String stmt = TERM_NS + "selectAfterTermStartingDate";
		return session.selectOne(stmt);
	}
	
	public Date getTermEndDate() {
		String stmt = TERM_NS + "selectTermEndDate";
		return session.selectOne(stmt);
	}
	
	/** 반장투표 인원수 확인 */
	public int getVoterCount(int lectureId) {
		String stmt = TERM_NS + "selectVoterCount";
		return session.selectOne(stmt, lectureId);
	}
	
	/** 반장투표 결과 적용 */
	public int updateVoting(Voting data) {
		String stmt = TERM_NS + "updateNotPresident";
		return session.update(stmt, data);
	}
	
	/** 반장을 원하는 사람이 없을 때 강제로 강의 수강인원 전체가 반장 투표하도록 DB 갱신*/
	public int updateCoercionVoter(int lectureId) {
		String stmt = TERM_NS + "updateCoercionPresident";
		return session.update(stmt, lectureId);
	}
}
