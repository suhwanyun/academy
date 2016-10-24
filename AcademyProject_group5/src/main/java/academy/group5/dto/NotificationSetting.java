package academy.group5.dto;

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
	private Integer notiTimeHour;
	
	/** 알림 시간(분). */
	private Integer notiTimeMin;

	/** 알림 간격. */
	private Integer notiTimeInterval;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notiOn == null) ? 0 : notiOn.hashCode());
		result = prime * result + ((notiTimeHour == null) ? 0 : notiTimeHour.hashCode());
		result = prime * result + ((notiTimeInterval == null) ? 0 : notiTimeInterval.hashCode());
		result = prime * result + ((notiTimeMin == null) ? 0 : notiTimeMin.hashCode());
		result = prime * result + ((notiType == null) ? 0 : notiType.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	

	/** 알림 목록. *//*
	private Set<Notifications> notificationsSet;*/
	
	
}
