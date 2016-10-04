package academy.group5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.UserData;
import academy.group5.repo.LoginRepo;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepo loginRepo;

	@Override
	public UserData login(String userId, String userPass) {
		UserData data = loginRepo.getUser(userId);
		if(data == null || !data.getUserPass().equals(userPass)){
			return null;
		}
		return data;
	}

	@Override
	public boolean join(UserData userdata) {
		int result = loginRepo.setUser(userdata);
		
		if(result != 1){
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(UserData userdata) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dropOut(String UserId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String findId(String userName, String phoneNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassQuestion(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPass(String userId, String passAnswer) {
		// TODO Auto-generated method stub
		return null;
	}

}
