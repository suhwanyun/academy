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
	
	/** 알림 요일 코드 */
	private char weekCode;
	
	/** 알림 시간(시). */
	private Integer notiHour;
	
	/** 알림 시간(분). */
	private Integer notiMin;

	public NotificationSetting(String notiType, String userId) {
		super();
		this.notiType = notiType;
		this.userId = userId;
	}
	
	
}
