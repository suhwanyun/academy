package academy.group5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.UserData;
import academy.group5.repo.PhoneRepo;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	PhoneRepo phoneRepo;
	
	@Override
	public int setGCMData(String userId, String phoneId) {
		
		return phoneRepo.setGCMData(new UserData(userId, null, phoneId));
	}

}
