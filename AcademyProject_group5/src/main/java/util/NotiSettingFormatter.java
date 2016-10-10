package util;

import java.text.ParseException;
import java.util.Locale;
import java.util.StringTokenizer;

import org.springframework.format.Formatter;

import academy.group5.dto.NotificationSetting;

/** 알림 시간 포멧 설정 */
public class NotiSettingFormatter implements Formatter<NotificationSetting>{

	@Override
	public String print(NotificationSetting object, Locale locale) {
		return object.getNotiTimeHour() + ":" + object.getNotiTimeMin();
	}

	@Override
	public NotificationSetting parse(String text, Locale locale) throws ParseException {
		NotificationSetting setting = new NotificationSetting();
		
		StringTokenizer tokens = new StringTokenizer(text, ":");
		setting.setNotiTimeHour(tokens.nextToken());
		setting.setNotiTimeMin(tokens.nextToken());

		return setting;
	}

}
