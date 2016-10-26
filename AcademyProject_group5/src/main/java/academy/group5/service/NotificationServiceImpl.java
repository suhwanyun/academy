package academy.group5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.etc.NotificationSettingList;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.NotificationRepo;

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
		return notiRepo.getNotificationSettingList(userId);
	}

	@Override
	public boolean settingModify(String userId, NotificationSettingList settingData) {
		
		List<NotificationSetting> existingSettingList = settingList(userId);
		List<NotificationSetting> newSettingList = settingData.getSettingList();
		
		// 설정값 이상
		if(existingSettingList.size() != SETTING_QUANTITY ||
			newSettingList.size() != SETTING_QUANTITY){
			throw new WrongRequestException();
		}
		
		for(int nSettingIdx = 0; nSettingIdx < SETTING_QUANTITY; nSettingIdx++){	
			// 기존 설정 데이터
			NotificationSetting nowSettingData = newSettingList.get(nSettingIdx);
			
			for(int eSettingIdx = 0; eSettingIdx < SETTING_QUANTITY; eSettingIdx++){
				// 새로운 설정 데이터
				NotificationSetting newSettingData = existingSettingList.get(eSettingIdx);
				// 해당 설정 값이면(id와 type이 일치)
				if(nowSettingData.equals(newSettingData)){
					// 설정이 변경되었으면 업데이트
					if(nowSettingData != newSettingData){
						int result = notiRepo.updateNotificationSetting(nowSettingData);
						// 업데이트 실패
						if(result != 1){
							throw new WrongRequestException();
						} 
					// 설정이 변경되지 않았으면 루프 탈출	
					} else {
						break;
					}
				}
				/** 루프 1회 종료 */
			}
			/** 설정 1회 완료 */
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
		settingDataList.add(new NotificationSetting("lecture", userId, 1, null, 7, 0, 10));
		settingDataList.add(new NotificationSetting("noti", userId, 1, null, 1, 19, 0));
		settingDataList.add(new NotificationSetting("place", userId, 1, null, 7, 9, 0));
		settingDataList.add(new NotificationSetting("play", userId, 1, null, 1, 22, 0));
		settingDataList.add(new NotificationSetting("food", userId, 1, null, 1, 11, 0));

		for(NotificationSetting settingData : settingDataList){
			int result = notiRepo.setNotificationSetting(settingData);
			
			if(result != 1){
				throw new WrongRequestException();
			}
		}
		return true;
	}

}
