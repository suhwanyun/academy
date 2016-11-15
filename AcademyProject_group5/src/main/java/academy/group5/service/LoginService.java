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
	 * 자동 로그인
	 * @param userId
	 * @param userPass
	 * @return
	 */
	UserData autoLogin(String userId, String encPass);
	/**
	 * 회원가입 서비스
	 * @param userdata
	 * @return
	 */
	boolean join(UserData userdata);
	/**
	 * 회원정보 수정
	 * @param userdata
	 * @return
	 */
	boolean update(UserData userdata);
	/**
	 * 회원 탈퇴
	 * @param UserId
	 * @return
	 */
	boolean dropOut(String UserId);
	/**
	 * 아이디 찾기
	 * @param userName
	 * @param email
	 * @return
	 */
	String findId(String userName, String email);
	
	/**
	 * 임시 비밀번호 받기
	 * @param userId
	 * @param answer
	 * @return
	 */
	String getPass(String userId, String answer);
	
	/**
	 * 존재하는 id인지 확인
	 * @param id
	 * @return
	 */
	boolean findUser(String id);
	
	/**
	 * 존재하는 이메일인지 확인
	 * @param email
	 * @return
	 */
	boolean findEmail(String email);
	
	/**
	 * 회원정보 확인
	 * @param id
	 * @return
	 */
	UserData getInfo(String id);
	
	/**
	 * PASSWORD 질문 확인
	 * @param id
	 * @return
	 */
	String getQuestion(String id);
}
