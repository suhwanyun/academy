package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.PhoneRepo;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	PhoneRepo phoneRepo;
	
	@Override
	public int setGCMData(String userId, String phoneId) {
		
		return phoneRepo.setGCMData(new UserData(userId, null, phoneId));
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
		return postingData;
	}

}
