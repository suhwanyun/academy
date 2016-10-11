package academy.group5.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import academy.group5.repo.TermRepo;
import academy.group5.scheduler.PresidentElectionScheduler;
import academy.group5.scheduler.TermEndScheduler;

public class AutoServiceImpl implements AutoService{
	
	private TermRepo termRepo;
	
	private AnnotationConfigApplicationContext voteContext;
	private AnnotationConfigApplicationContext termContext;
	
	public AutoServiceImpl(TermRepo termRepo){
		this.termRepo = termRepo;
		startVoteScheduler();
		startTermScheduler();
	}
	
	public void startVoteScheduler(){
		
		if(termRepo.getTodayTerm() == null){
			voteContext = new AnnotationConfigApplicationContext(PresidentElectionScheduler.class);
		}	
	}
	
	public void startTermScheduler(){
		
		if(termRepo.getTodayTerm() != null){
			termContext = new AnnotationConfigApplicationContext(TermEndScheduler.class);
		}	
	}
}
