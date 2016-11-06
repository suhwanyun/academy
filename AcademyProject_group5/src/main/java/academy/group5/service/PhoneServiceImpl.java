package academy.group5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.PhoneRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

	/** 메세지 출력 최대 길이 */
	private final int MAX_MSG_CONTENT_LENGTH = 20;
	
	@Autowired
	PhoneRepo phoneRepo;
	
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
	public List<NotificationSetting> getNotificationSettingList(String userId) {
		
		return phoneRepo.getNotificationSettingList(userId);
	}

	@Override
	public Posting getNotificationData(String postingType) {
		// 추천수 집계 기간 설정
		int recommendPeriod = MostRecommend.PERIOD_DAY; 
		if(postingType.equals("place")){
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
		String postingContent = postingData.getPostingContent();
		if(postingContent.length() >= MAX_MSG_CONTENT_LENGTH){
			postingData.setPostingContent(postingContent + "...");
		}
		
		return postingData;
	}

}
