package academy.group5.service;

import java.util.List;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Notifications;

public interface NotificationService {
	/**
	 * 알림 설정 목록 가져오기
	 * @param userId
	 * @return
	 */
	List<NotificationSetting> settingList(String userId);
	/**
	 * 알람 설정 수정
	 * @param notificationsetting
	 * @return
	 */
	boolean settingModify(NotificationSetting notificationsetting);
	
	/**
	 * 알림 송신
	 * @param notiType
	 * @param notiTitle
	 * @param notiContent
	 * @return
	 */
	boolean sendNotification(String notiType, String notiTitle, String notiContent);
	
}
