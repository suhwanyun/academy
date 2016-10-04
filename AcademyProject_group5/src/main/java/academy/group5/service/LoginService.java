package academy.group5.service;

import academy.group5.dto.UserData;

public interface LoginService {
	/**
	 * 회원 ID와Pass를 받아서 회원의 정보를 리턴
	 * @param userId
	 * @param userPass
	 * @return
	 */
	UserData getUserData(String userId, String userPass);
	
	/**
	 * 회원의 이름과 핸드폰을 받아서 회원 ID를 리턴
	 * @param userName
	 * @param phoneNum
	 * @return
	 */
	String getUserId(String userName, String phoneNum);
	/**
	 * 회원Id를 받아서 비밀번호 질문을 리턴
	 * @param userId
	 * @return
	 */
	String getPassQuestion(String userId);
	/**
	 * 회원Id와 비밀번호 답을 받아서 비밀번호를 리턴
	 * @param userId
	 * @param passAnswer
	 * @return
	 */
	String getUserPass(String userId, String passAnswer);
	
}
