package academy.group5.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import academy.group5.repo.TermRepo;
import academy.group5.scheduler.MyScheduler;

@Service
public class AutoServiceImpl implements AutoService {

	@Autowired
	MyScheduler scheduler;
	
	@Autowired
	TermRepo termRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(AutoServiceImpl.class);
	
	@PostConstruct
	public void startVoteScheduler() {
		
		Date nextTermDate = getScheduleTime(termRepo.getNextTermStartDate());
		
		if(nextTermDate == null){
			return;
		}
		
		scheduler.taskScheduler().schedule(new Runnable() {
			public void run() {
				logger.trace("schedule!!");
			}
		}, nextTermDate);
	}
	
	public void startTermScheduler() {

	}
	
	private Date getScheduleTime(Date aimTime){
		if(aimTime == null){
			return null;
		}
		Date nowDate = Calendar.getInstance().getTime();
		long diffMillis = aimTime.getTime()- nowDate.getTime();
		
		if(diffMillis <= 0){
			return null;
		}
		
		return aimTime;
	}
}
