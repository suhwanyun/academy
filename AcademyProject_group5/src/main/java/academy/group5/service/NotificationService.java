package academy.group5.service;

import java.util.List;

import academy.group5.dto.NotificationSetting;

public interface NotificationService {
	/**
	 * 알림 설정 목록 가져오기
	 * @param userId
	 * @return
	 */
	List<NotificationSetting> settingList(String userId);
	/**
	 * 알람 수정
	 * @param notificationsetting
	 * @return
	 */
	boolean settingModify(NotificationSetting notificationsetting);
	
}
