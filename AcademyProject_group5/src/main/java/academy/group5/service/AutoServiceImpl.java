package academy.group5.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import academy.group5.config.SchedulerConfig;
import academy.group5.dto.Term;

@Service
public class AutoServiceImpl implements AutoService{

	private AnnotationConfigApplicationContext context;
	@Override
	public void voteForPresident(Term term) {
		if(term == null){
			context = new AnnotationConfigApplicationContext(SchedulerConfig.class);
		}
		
	}

	@Override
	public void endTerm(Term term) {
		
		
	}

}
