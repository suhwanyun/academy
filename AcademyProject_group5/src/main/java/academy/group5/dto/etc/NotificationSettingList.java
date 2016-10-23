package academy.group5.dto.etc;

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
	/** 알림 설정 목록 */
	private List<NotificationSetting> settingList;
}
