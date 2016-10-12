package academy.group5.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.group5.dto.etc.Voting;
import academy.group5.repo.LectureRepo;
import academy.group5.repo.TermRepo;
import academy.group5.scheduler.MyScheduler;

@Service
public class AutoServiceImpl implements AutoService {

	@Autowired
	MyScheduler scheduler;
	
	@Autowired
	TermRepo termRepo;
	
	@Autowired
	LectureRepo lectureRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(AutoServiceImpl.class);
	
	@PostConstruct
	public void startVoteScheduler() {
		
		Date nextTermDate = getScheduleTime(termRepo.getNextTermStartDate());
		
		if(nextTermDate == null){
			return;
		}
		
		scheduler.taskScheduler().schedule(new Runnable() {
			public void run() {
				List<Integer> lectureIdList = lectureRepo.getAllLectureId();
				for(Integer lectureId : lectureIdList){
					int voterCount = termRepo.getVoterCount(lectureId);
					
					// 강제로 투표
					if(voterCount == 0){
						voterCount = termRepo.updateCoercionVoter(lectureId);
					}
					// 아예 강의를 신청한 인원이 없음
					if(voterCount == 0){
						continue;
					}
					Random random = new Random();
					int votingResult = random.nextInt(voterCount) + 1;
					termRepo.updateVoting(new Voting(lectureId, votingResult));
					
				}
			}
		}, nextTermDate);
	}
	
	public void startTermScheduler() {
		Date nextTermDate = getScheduleTime(termRepo.getTermEndDate());
		
		if(nextTermDate == null){
			return;
		}
		
		scheduler.taskScheduler().schedule(new Runnable() {
			public void run() {
				logger.trace("schedule!!");
			}
		}, nextTermDate);
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
