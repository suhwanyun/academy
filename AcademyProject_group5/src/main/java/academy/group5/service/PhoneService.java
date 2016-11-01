package academy.group5.service;

import java.util.List;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;

public interface PhoneService {
	/**
	 * GCM 등록
	 */
	boolean setGCMData(String userId, String phoneId);
	
	/**
	 * GCM 등록 해지
	 * @param userId
	 * @return
	 */
	boolean removeGCMData(String userId);

	/**
	 * 알람 설정 정보 획득
	 * @param userId
	 * @return
	 */
	List<NotificationSetting> getNotificationSettingList(String userId);
	
	/**
	 * 알람에 사용할 데이터 획득
	 * @param postingType
	 * @return
	 */
	Posting getNotificationData(String postingType);
}
