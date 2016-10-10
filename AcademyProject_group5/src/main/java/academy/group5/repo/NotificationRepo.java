package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Notifications;

@Repository
public class NotificationRepo {
	
	private final String NOTI_NS = "academy.repo.NotificationMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	public List<NotificationSetting> getNotificationSettingList(String id) {
		String stmt = NOTI_NS + "selectNotificationSetting";
		return session.selectList(stmt, id);
	}
	
	public int setNotificationSetting(NotificationSetting data) {
		String stmt = NOTI_NS + "insertNotificationSetting";
		return session.insert(stmt, data);
	}
	
	public int updateNotificationSetting(NotificationSetting data) {
		String stmt = NOTI_NS + "updateNotificationSetting";
		return session.update(stmt, data);
	}
	
	public int insertNotification(Notifications data) {
		String stmt = NOTI_NS + "insertNotification";
		return session.insert(stmt, data);
	}
}
