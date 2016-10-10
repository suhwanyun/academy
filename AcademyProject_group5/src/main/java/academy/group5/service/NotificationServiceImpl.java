package academy.group5.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.NotificationSetting;
import academy.group5.repo.NotificationRepo;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepo notiRepo;
	
	@Override
	public List<NotificationSetting> settingList(String userId) {
		return notiRepo.getNotificationSettingList(userId);
	}

	@Override
	public boolean settingModify(NotificationSetting notificationsetting) {

		int result = notiRepo.setNotificationSetting(notificationsetting);
		
		if(result == 1){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public boolean sendNotification(String notiType, String notiTitle, String notiContent) {
		
		return false;
	}

	@Override
	public boolean settingSet(String userId) {		

		NotificationSetting[] settingList = {
				new NotificationSetting("lecture", userId, 1, 0, 10, 7, null),
				new NotificationSetting("noti", userId, 1, 0, 10, 7, null),
				new NotificationSetting("place", userId, 1, 0, 10, 7, null),
				new NotificationSetting("play", userId, 1, 0, 10, 1, null),
				new NotificationSetting("food", userId, 1, 0, 10, 1, null)
				};
		
		for(NotificationSetting setting : settingList){
			notiRepo.setNotificationSetting(setting);
		}
		return false;
	}

}
