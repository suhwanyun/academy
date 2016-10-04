package academy.group5.service;

import academy.group5.dto.UserData;

public interface LoginService {
	/**
	 * 로그인
	 * @param userId
	 * @param userPass
	 * @return
	 */
	UserData login(String userId, String userPass);
	/**
	 * 회원가입 서비스
	 * @param userdata
	 * @return
	 */
	boolean join(UserData userdata);
	/**
	 * 회원 탈퇴
	 * @param UserId
	 * @return
	 */
	boolean dropOut(String UserId);
	/**
	 * 아이디 찾기
	 * @param userName
	 * @param phoneNum
	 * @return
	 */
	String findId(String userName, String phoneNum);
	/**
	 * 비밀번호 질문받아오기
	 * @param userId
	 * @return
	 */
	String getPassQuestion(String userId);
	/**
	 * 비밀번호 찾기
	 * @param userId
	 * @param passAnswer
	 * @return
	 */
	String findPass(String userId, String passAnswer);
	
	
}
