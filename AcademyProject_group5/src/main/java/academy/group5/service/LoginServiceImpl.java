package academy.group5.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.UserData;
import academy.group5.dto.etc.MyHash;
import academy.group5.dto.etc.UserId;
import academy.group5.dto.etc.UserPass;
import academy.group5.repo.LoginRepo;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	LoginRepo loginRepo;

	// 로그인
	@Override
	public UserData login(String userId, String userPass) {
		UserData data = loginRepo.getUser(userId);

		if(data == null || !data.getUserPass().equals(MyHash.MD5(userPass))){
			return null;
		}
		return data;
	}

	// 회원가입
	@Override
	public boolean join(UserData userdata) {
		String id = userdata.getUserId();
		// 아이디 중복 방지
		if(id == null || id.equals("") || !findUser(userdata.getUserId())){
			return false;
		}
		
		UserData encdata = toHash(userdata);
		logger.trace("data: {}", encdata);
		try{
			loginRepo.setUser(encdata);
		}catch(org.springframework.dao.DuplicateKeyException e){
			return false;
		}
		
		return true;
	}

	// 정보 수정
	@Override
	public boolean update(UserData userdata) {
		UserData encdata = toHash(userdata);
		
		int result = loginRepo.updateUser(encdata);
		
		if(result != 1){
			return false;
		}
		
		return true;
	}

	// 회원 탈퇴
	@Override
	public boolean dropOut(String UserId) {
		int result = loginRepo.deleteUser(UserId);
		
		if(result != 1){
			return false;
		}
		
		return true;
	}

	// 아이디 찾기
	@Override
	public String findId(String userName, Integer phoneNum) {
		return loginRepo.getUserId(new UserId(userName, phoneNum));
	}

	// 비밀번호 질문 찾기
	@Override
	public String getPassQuestion(String userId) {
		return loginRepo.getQuestion(userId);
	}
	
	// 암호화
	private UserData toHash(UserData data){
		String oriPass = data.getUserPass();

		if(oriPass != null && !oriPass.equals("")){
			String newPass = MyHash.MD5(oriPass);
			data.setUserPass(newPass);
		}
		
		return data;
	}

	// 존재하는 아이디인지 확인
	@Override
	public boolean findUser(String id) {
		int finded = loginRepo.findId(id);
		
		if(finded == 0){
			return true;
		} else{
			return false;
		}
	}

}
