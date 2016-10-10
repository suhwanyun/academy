package academy.group5.service;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean settingSet(NotificationSetting notificationsetting) {
		// TODO Auto-generated method stub
		return false;
	}

}
