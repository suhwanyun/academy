package academy.group5.service;

import java.util.List;

import academy.group5.dto.NotificationSetting;

public interface PhoneService {
	/**
	 * GCM 등록
	 */
	int setGCMData(String userId, String phoneId);

	/**
	 * 알람 설정 정보 획득
	 * @param userId
	 * @return
	 */
	List<NotificationSetting> getNotificationSettingList(String userId);
}
