package academy.group5.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.UserData;
import academy.group5.dto.etc.UserId;
import academy.group5.dto.etc.UserPass;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.LoginRepo;
import academy.group5.util.MyHash;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginRepo loginRepo;
	
	@Autowired
	NotificationService notificationService;

	/** 로그인 */
	@Override
	public UserData login(String userId, String userPass) {
		UserData data = loginRepo.getUser(userId);
		String encPass = loginRepo.getEncPass(userId);
		
		if(data == null || !encPass.equals(MyHash.MD5(userPass))){
			return null;
		}
		return data;
	}

	/** 회원가입 */
	@Override
	public boolean join(UserData userdata) {
		String id = userdata.getUserId();
		// 아이디/이메일 중복 방지
		if(id == null || id.equals("") ||
				!findUser(userdata.getUserId()) ||
				!findEmail(userdata.getEmail()) ){
			throw new WrongRequestException();
		}
		UserData encdata = toHash(userdata);
		
		loginRepo.setUser(encdata);
		notificationService.settingSet(userdata.getUserId());
		
		return true;
	}

	/** 정보 수정 */
	@Override
	public boolean update(UserData userdata) {
		UserData encdata = toHash(userdata);
		
		int result = loginRepo.updateUser(encdata);
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	/** 회원 탈퇴 */
	@Override
	public boolean dropOut(String UserId) {
		int result = loginRepo.deleteUser(UserId);
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	/** 아이디 찾기 */
	@Override
	public String findId(String userName, String email) {
		return loginRepo.getUserId(new UserId(userName, email));
	}
	
	/** 임시 비밀번호 받기 */
	@Override
	public String getPass(String userId, String answer) {
		if(loginRepo.getEncPass(new UserPass(userId, answer)) != null){
			String tmpPass = generatePass();
			
			UserData encdata = toHash(new UserData(userId, tmpPass));
		
			int result = loginRepo.updatePass(encdata);
			
			if(result == 1){
				// 생성된 임시 비밀번호
				return tmpPass;
			}
		}
		return "";
	}

	/** 존재하는 아이디인지 확인 */
	@Override
	public boolean findUser(String id) {
		int finded = loginRepo.findUser(id);
		
		if(finded == 0){
			return true;
		} else{
			return false;
		}
	}
	
	/** 존재하는 이메일인지 확인 */
	@Override
	public boolean findEmail(String email) {
		int finded = loginRepo.findEmail(email);
		
		if(finded == 0){
			return true;
		} else{
			return false;
		}
	}
	
	/** 회원정보 확인 */
	@Override
	public UserData getInfo(String id) {
		UserData result = loginRepo.getInfo(id);
		
		if(result == null){
			throw new WrongRequestException();
		} 	
		return result;
	}
	
	@Override
	public String getQuestion(String id){
		String result = loginRepo.getQuestion(id);
		
		if(result == null){
			return "";
		} 	
		return result;
	}
	
	/** 암호화 */
	private UserData toHash(UserData data){
		String oriPass = data.getUserPass();

		if(oriPass != null && !oriPass.equals("")){
			String newPass = MyHash.MD5(oriPass);
			data.setUserPass(newPass);
		}
		
		return data;
	}
	
	/** 암호 생성 */
	private String generatePass(){
		
		Random rand = new Random();
		String newPass = "";
		
		for(int index = 1; index <= 10; index++){
			int select = rand.nextInt(3);
			String randData;
			
			switch(select){
			// 숫자(0~9)
			case 0:
				randData = Integer.toString(rand.nextInt(10));
				break;
			// 소문자(a~z)
			case 1:
				randData = String.valueOf((char)(rand.nextInt(26) + 97));
				break;
			// 대문자(A-Z)
			default:
				randData = String.valueOf((char)(rand.nextInt(26) + 65));
				break;
			}
			
			newPass += randData;
		}
		
		return newPass;
	}

}
