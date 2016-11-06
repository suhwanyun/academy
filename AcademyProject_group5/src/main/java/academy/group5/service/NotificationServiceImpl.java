package academy.group5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.etc.NotificationSettingList;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.NotificationRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{

	private final int SETTING_QUANTITY = 4;
	@Autowired
	NotificationRepo notiRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	@Override
	public NotificationSettingList getSettingList(String userId) {
		List<NotificationSetting> settingList = notiRepo.getNotificationSettingList(userId);
		
		if(settingList.size() < SETTING_QUANTITY){
			throw new WrongRequestException();
		}
		NotificationSettingList settingDto = new NotificationSettingList();
		settingDto.setSettingList(settingList);
		
		return settingDto;
	}

	@Override
	public boolean settingModify(String userId, NotificationSettingList settingData) {
		
		List<NotificationSetting> settingList = settingData.getSettingList();
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

		char allday = 0x7f; // 0111 1111
		char weekday = 0x7C; // 0111 1100
		char weekend = 0x03; // 0000 0011
		
		List<NotificationSetting> settingDataList = new ArrayList<>();
		// 알림 설정 초기화
		settingDataList.add(new NotificationSetting(Posting.TYPE_LECTURE, userId, 1, allday, 0, 10));
		settingDataList.add(new NotificationSetting(Posting.TYPE_FOOD, userId, 1, weekday, 11, 0));
		settingDataList.add(new NotificationSetting(Posting.TYPE_PLAY, userId, 1, weekday, 22, 0));
		settingDataList.add(new NotificationSetting(Posting.TYPE_PLACE, userId, 1, weekend, 9, 0));

		for(NotificationSetting settingData : settingDataList){
			int result = notiRepo.setNotificationSetting(settingData);
			
			if(result != 1){
				throw new WrongRequestException();
			}
		}
		return true;
	}

}
