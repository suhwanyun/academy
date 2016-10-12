package academy.group5.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.Term;

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
		Date result = session.selectOne(stmt);

		return result;
	}
}
