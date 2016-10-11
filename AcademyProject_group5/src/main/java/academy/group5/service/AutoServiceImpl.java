package academy.group5.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import academy.group5.scheduler.PresidentElectionScheduler;
import academy.group5.scheduler.TermEndScheduler;

public class AutoServiceImpl implements AutoService{
	
	public AutoServiceImpl(){
		startVoteScheduler();
		startTermScheduler();
	}
	
	public void startVoteScheduler(){
		AnnotationConfigApplicationContext voteContext = new AnnotationConfigApplicationContext(PresidentElectionScheduler.class);	
	}
	
	public void startTermScheduler(){
		AnnotationConfigApplicationContext termContext = new AnnotationConfigApplicationContext(TermEndScheduler.class);
			
	}
}
