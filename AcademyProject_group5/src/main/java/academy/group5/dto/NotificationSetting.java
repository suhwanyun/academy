package academy.group5.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림설정 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSetting {

	/** 알림 종류. */
	private String notiType;

	/** 회원 ID. */
	private String userId;

	/** 알림 여부. */
	private Integer notiOn;

	/** 알림 시간(시). */
	private String notiTimeHour;
	
	/** 알림 시간(분). */
	private String notiTimeMin;

	/** 알림 간격. */
	private Integer notiTimeInterval;

	/** 알림 목록. *//*
	private Set<Notifications> notificationsSet;*/

	/** 알림 설정 목록 */
	private List<NotificationSetting> settingList;
	
	/** 알림 설정 목록 getter */
	public List<NotificationSetting> getSettingList(){
		return settingList;
	}
	
	/** 알림 설정 목록 setter */
	public void setSettingList(List<NotificationSetting> settingList){
		this.settingList = settingList;
	}
	
	
	
}
