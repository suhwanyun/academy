package academy.group5.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.Term;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.LectureTimeForPhone;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.dto.etc.TermForPhone;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.PhoneRepo;
import academy.group5.repo.TermRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

	/** 메세지 출력 최대 길이 */
	private final int MAX_MSG_CONTENT_LENGTH = 20;
	
	@Autowired
	PhoneRepo phoneRepo;
	
	@Autowired
	TermRepo termRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	@Override
	public boolean setGCMData(String userId, String phoneId) {
		
		int result = -1;
		try {
			result = phoneRepo.setGCMData(new UserData(userId, null, phoneId));
		}catch(DataAccessException e){
			return false;
		}
		if(result != 1){
			return false;
		}
		// 기기가 연결되면 알람 설정 반영
		List<String> userIdList = new ArrayList<>();
		userIdList.add(phoneId);
		
		new GCM(null, null, userIdList, GCM.TYPE_SETTING);
		
		return true;
	}
	
	@Override
	public boolean removeGCMData(String userId){
		
		String phoneId = gcmRepo.getOneUser(userId);
		List<String> userIdList = new ArrayList<>();
		userIdList.add(phoneId);
		
		new GCM(null, null, userIdList, GCM.TYPE_RESET);
		
		int result = -1;
		try {
			result = phoneRepo.resetGCMData(userId);
		}catch(DataAccessException e){
			return false;
		}
		if(result != 1){
			return false;
		}
		return true;
	}
	
	@Override
	public TermForPhone getTermData(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		TermForPhone termData = phoneRepo.getTerm();
		if(termData == null){
			termData = new TermForPhone();
			Date startDate = termRepo.getNextTermStartDate();
			Date endDate = termRepo.getTermEndDate();
			if(startDate == null || endDate == null){
				return null;
			}
			termData.setTermStart(format.format(startDate));
			termData.setTermEnd(format.format(endDate));
		}
		return termData;
	}

	@Override
	public List<NotificationSetting> getNotificationSettingList(String userId) {
		
		return phoneRepo.getNotificationSettingList(userId);
	}

	@Override
	public Posting getNotificationData(String postingType) {
		// 추천수 집계 기간 설정
		int recommendPeriod = MostRecommend.PERIOD_DAY; 
		if(postingType.equals(Posting.TYPE_PLACE)){
			recommendPeriod = MostRecommend.PERIOD_WEEK;
		}
		
		Posting postingData = phoneRepo.getMostRecommendPosting(new MostRecommend(postingType, recommendPeriod));
		// 집계 기간 동안 추천을 받은 게시글이 하나도 없으면 가장 최신글을 검색
		if(postingData == null){
			postingData = phoneRepo.getNewestPosting(postingType);
		}
		if(postingData == null){
			throw new WrongRequestException();
		}
		// 메세지가 최대 길이면 줄임말 표시 추가
		String postingTitle = postingData.getPostingTitle();
		if(postingTitle.length() >= MAX_MSG_CONTENT_LENGTH){
			postingData.setPostingTitle(postingTitle + "...");
		}
		String postingContent = postingData.getPostingContent();
		if(postingContent.length() >= MAX_MSG_CONTENT_LENGTH){
			postingData.setPostingContent(postingContent + "...");
		}
		
		return postingData;
	}
	
	@Override
	public List<LectureTimeForPhone> getLectureTimeList(String userId){
		
		return phoneRepo.getLectureTimeList(userId);
	}

}
