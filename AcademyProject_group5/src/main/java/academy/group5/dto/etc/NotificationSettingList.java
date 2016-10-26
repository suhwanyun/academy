package academy.group5.dto.etc;

import java.util.Date;
import java.util.List;

import academy.group5.dto.NotificationSetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림 설정에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSettingList {
	
	/** 알림 종류. */
	private List<String> notiType;

	/** 회원 ID. */
	private List<String> userId;

	/** 알림 여부. */
	private List<Integer> notiOn;
	
	/** 다음 알림 날짜 */
	private List<Date> nextNotiDate;
	
	/** 알림 간격. */
	private List<Integer> notiTimeInterval;
	
	/** 알림 시간(시). */
	private List<Integer> notiHour;
	
	/** 알림 시간(분). */
	private List<Integer> notiMin;
}
