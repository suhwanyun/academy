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
	
	/** 다음 알림 날짜 */
	private Date nextNotiDate;
	
	/** 알림 간격. */
	private Integer notiTimeInterval;
	
	/** 알림 시간(시). */
	private Integer notiHour;
	
	/** 알림 시간(분). */
	private Integer notiMin;
	
	// 알림 설정 목록(데이터 전달용)
	private List<NotificationSetting> NotificationSettingList;
	
	public NotificationSetting(String notiType, String userId, Integer notiOn, Integer notiTimeInterval,
			Integer notiHour, Integer notiMin) {
		super();
		this.notiType = notiType;
		this.userId = userId;
		this.notiOn = notiOn;
		this.notiTimeInterval = notiTimeInterval;
		this.notiHour = notiHour;
		this.notiMin = notiMin;
	}	

	/** userId 와 notiType만 일치하면 true */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationSetting other = (NotificationSetting) obj;
		if (notiType == null) {
			if (other.notiType != null)
				return false;
		} else if (!notiType.equals(other.notiType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/** 다음 알림 날짜는 무관 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notiOn == null) ? 0 : notiOn.hashCode());
		result = prime * result + ((notiHour == null) ? 0 : notiHour.hashCode());
		result = prime * result + ((notiTimeInterval == null) ? 0 : notiTimeInterval.hashCode());
		result = prime * result + ((notiMin == null) ? 0 : notiMin.hashCode());
		result = prime * result + ((notiType == null) ? 0 : notiType.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	/*// 알림 목록
	private Set<Notifications> notificationSet;*/
}
