package academy.group5.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Mileage;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.dto.etc.Voting;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.LectureRepo;
import academy.group5.repo.MileageRepo;
import academy.group5.repo.TermRepo;
import academy.group5.scheduler.MyScheduler;
import academy.group5.util.GCM;

@Service
@Transactional
public class AutoServiceImpl implements AutoService {

	@Autowired
	MyScheduler scheduler;
	
	@Autowired
	TermRepo termRepo;
	
	@Autowired
	LectureRepo lectureRepo;
	
	@Autowired
	MileageRepo mileageRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	// 스케줄러 동작 여부 플래그
	private boolean nowVoteScheduling;
	private boolean nowTermScheduling;
	
	public AutoServiceImpl(){
		nowVoteScheduling = false;
		nowTermScheduling = false;
	}
	
	@PostConstruct
	public void startVoteScheduler() {
		// 개강일
		Date nextTermDate = getScheduleTime(termRepo.getNextTermStartDate());
		
		// 다음 학기 개강일이 없거나 이미 스케줄러가 동작한 경우 취소
		if(nextTermDate == null || nowVoteScheduling){
			return;
		}
		nowVoteScheduling = true;
		
		scheduler.taskScheduler().schedule(new Runnable() {
			public void run() {
				// 반장들의 핸드폰 ID 정보가 담길 리스트(중복 금지)
				Set<String> presidentIdList = new HashSet<>();
				// 전체 강의ID 리스트
				List<Integer> lectureIdList = lectureRepo.getAllLectureId();
				
				for(Integer lectureId : lectureIdList){
					// 이 강의의 반장이 되기를 원하는 학생 수
					int voterCount = termRepo.getVoterCount(lectureId);
					
					// 반장을 신청한 사람이 없을 경우 강제로 전체 인원 중 선출
					if(voterCount == 0){
						voterCount = termRepo.updateCoercionVoter(lectureId);
					}
					// 아예 강의를 신청한 인원이 없음
					if(voterCount == 0){
						continue;
					}
					Random random = new Random();
					int votingResult = random.nextInt(voterCount) + 1;
					// 반장 선출 결과를 DB에 저장
					termRepo.updateVoting(new Voting(lectureId, votingResult));
					
					// 선출된 반장의 핸드폰ID정보 리스트화
					String presidentId = gcmRepo.getPresident(lectureId);
					if(presidentId != null){
						presidentIdList.add(presidentId);
					}
					
				}
				nowVoteScheduling = false;
				
				// 축하 메세지 전송
				List<String> sendData = new ArrayList<>(presidentIdList);
				new GCM("축하합니다! 반장에 선출되셨습니다!", "자세한 정보는 내 강의목록에서 확인해주세요.", sendData, GCM.TYPE_NOTICE);
				// 반복
				startVoteScheduler();
			}
		}, nextTermDate);
	}
	
	@PostConstruct
	public void startTermScheduler() {
		// 종강일
		Date nextTermDate = getScheduleTime(termRepo.getTermEndDate());
		
		// 다음 학기 종강일이 없거나 이미 스케줄러가 동작한 경우 취소
		if(nextTermDate == null || nowTermScheduling){
			return;
		}
		nowTermScheduling = true;
		
		scheduler.taskScheduler().schedule(new Runnable() {
			public void run() {
				// DB 데이터 삭제
				termRepo.deleteAllLectureRecommend();
				termRepo.deleteAllLectureComment();
				termRepo.deleteAllLecturePosting();
				
				termRepo.deleteAllLectureApply();
				termRepo.deleteAllLectureNotice();
				termRepo.deleteAllCancelLecture();
				termRepo.deleteAllLectureTime();
				termRepo.deleteAllLecture();
				
				nowTermScheduling = false;
				
				// 전체 공지
				List<String> userList = gcmRepo.getAllUser();
				new GCM("수고하셨습니다.", "학기가 종료되어 강의 데이터가 초기화 되었습니다.", userList, GCM.TYPE_NOTICE);
				// 반복
				startTermScheduler();
			}
		}, nextTermDate);
	}
	
	/**
	 * 추천수 많은 게시물 작성자 마일리지 획득
	 */
	@PostConstruct
	public void startRecommendMileageScheduler() {
		// 다음날 0시로 설정
		Calendar calInst = Calendar.getInstance();
		calInst.add(Calendar.DAY_OF_MONTH, 1);
		calInst.set(Calendar.HOUR_OF_DAY, 0);
		calInst.set(Calendar.MINUTE, 0);
		calInst.set(Calendar.SECOND, 0);
		
		scheduler.taskScheduler().schedule(new Runnable() {
			public void run() {
				// 게시판 검색 조건 설정
				List<MostRecommend> mostRecommendSettingList = new ArrayList<>();
				mostRecommendSettingList.add(new MostRecommend(Posting.TYPE_FOOD, MostRecommend.PERIOD_DAY));
				mostRecommendSettingList.add(new MostRecommend(Posting.TYPE_PLAY, MostRecommend.PERIOD_DAY));
				mostRecommendSettingList.add(new MostRecommend(Posting.TYPE_PLACE, MostRecommend.PERIOD_WEEK));
				
				// 축하 메세지를 전달 받을 회원의 핸드폰 ID 목록
				Set<String> phoneIdList = new HashSet<>();
				
				for(MostRecommend mostRecommendSetting : mostRecommendSettingList) {
					UserData targetUserData = new UserData();
					// 게시판별 추천수가 가장많은 게시글 작성자 확인
					String userId = mileageRepo.getMostRecommendPostingUserId(mostRecommendSetting);
					if(userId == null){
						continue;
					}
					targetUserData.setUserId(userId);
					// 게시글 작성자의 마일리지 확인
					Integer userMileage = mileageRepo.getMileage(userId);
					if(userMileage == null){
						continue;
					} else if(mostRecommendSetting.getPeriod() == MostRecommend.PERIOD_WEEK){
						targetUserData.setUserMileage(userMileage + Mileage.MILEAGE_MOST_RECOMMEND_WEEK);
					} else {
						targetUserData.setUserMileage(userMileage + Mileage.MILEAGE_MOST_RECOMMEND_DAY);
					}
					// 마일리지 추가
					int result = mileageRepo.setMileage(targetUserData);
					// 추하 메세지 전달 준비
					String phoneId = gcmRepo.getOneUser(userId);
					if(result != 0 && phoneId != null){
						phoneIdList.add(phoneId);
					}
					
				}
				
				// 축하 메세지 송신
				new GCM("축하합니다!", "오늘의 최고 게시글에 뽑히셨습니다!", phoneIdList, GCM.TYPE_NO_SOUND);
				// 반복
				startTermScheduler();
			}
		}, calInst.getTime());
	}
	
	/** 이미 지난 날짜인지 확인 */
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
