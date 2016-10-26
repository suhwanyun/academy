package academy.group5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.NotificationRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{

	private final int SETTING_QUANTITY = 5;
	@Autowired
	NotificationRepo notiRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	@Override
	public List<NotificationSetting> settingList(String userId) {
		List<NotificationSetting> settingList = notiRepo.getNotificationSettingList(userId);
		
		if(settingList.size() < SETTING_QUANTITY){
			throw new WrongRequestException();
		}
		return settingList;
	}

	@Override
	public boolean settingModify(String userId, NotificationSetting settingData) {
		
		List<NotificationSetting> settingList = settingData.getNotificationSettingList();
		// 설정값 이상
		if(settingList.size() != SETTING_QUANTITY){
			throw new WrongRequestException();
		}
		
		for(NotificationSetting data : settingList){	
			int result = notiRepo.updateNotificationSetting(data);
			// 업데이트 실패
			if(result != 1){
				throw new WrongRequestException();
			} 
		}
		
		// 기기가 연동된 회원이면 설정 반영
		String phoneId = gcmRepo.getOneUser(userId);
		if(phoneId != null){
			List<String> userIdList = new ArrayList<>();
			userIdList.add(phoneId);
			
			new GCM(null, null, userIdList, GCM.TYPE_SETTING);
		}
		return true;
	}

	@Override
	public boolean sendNotification(String notiType, String notiTitle, String notiContent) {
		
		return false;
	}

	@Override
	public boolean settingSet(String userId) {		

		List<NotificationSetting> settingDataList = new ArrayList<>();
		
		// 알림 설정 초기화
		settingDataList.add(new NotificationSetting("lecture", userId, 1, 7, 0, 10));
		settingDataList.add(new NotificationSetting("noti", userId, 1, 1, 19, 0));
		settingDataList.add(new NotificationSetting("food", userId, 1, 1, 11, 0));
		settingDataList.add(new NotificationSetting("play", userId, 1, 1, 22, 0));
		settingDataList.add(new NotificationSetting("place", userId, 1, 7, 9, 0));

		for(NotificationSetting settingData : settingDataList){
			int result = notiRepo.setNotificationSetting(settingData);
			
			if(result != 1){
				throw new WrongRequestException();
			}
		}
		return true;
	}

}
