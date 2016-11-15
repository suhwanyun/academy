package academy.group5.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import academy.group5.dto.UserData;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.LoginService;
import academy.group5.service.PhoneService;
import academy.group5.util.MyHash;

/**
 * 계정 관련 컨트롤러
 * @author YSH
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;	
	
	@Autowired
	PhoneService phoneService;
	 
	/** 회원가입시 아이디 중복확인 */
	@RequestMapping(value="/findUser", method=RequestMethod.GET)
	public @ResponseBody String findUser(@RequestParam String userId){
		
		if(loginService.findUser(userId)){
			return "true";
		} else{
			return "false";
		}				
	}
	
	/** 회원가입시 이메일 중복확인 */
	@RequestMapping(value="/findEmail", method=RequestMethod.GET)
	public @ResponseBody String findEmail(@RequestParam String email){
		
		if(loginService.findEmail(email)){
			return "true";
		} else{
			return "false";
		}	
	}
	
	/** 회원가입 완료 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(HttpSession session, UserData data){
		
		data.setUserMileage(new Integer(0));
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/main");
		session.setAttribute("gotoPage", "/main");
		
		loginService.join(data);
				
		throw new PageRedirectException("회원가입 되었습니다.");
	}
	
	/** 로그인 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(Model model, 
			HttpSession session, HttpServletResponse response,
			@RequestParam String userId, @RequestParam String userPass){
		
		try{
			UserData data = loginService.login(userId, userPass);
			session.setAttribute("user", data);
		}catch(WrongRequestException e){
			return "false";
		}
		
		Object isPhone = session.getAttribute("isPhone");
		// 핸드폰으로 로그인시 자동 로그인 설정
		if(isPhone != null && isPhone.equals("true")) {
			final int COOKIE_AGE_YEAR = 60*60*24*365;
			
			Cookie idCookie = new Cookie("id", userId);
			Cookie passCookie = new Cookie("encPass", MyHash.MD5(userPass));
			idCookie.setMaxAge(COOKIE_AGE_YEAR);
			passCookie.setMaxAge(COOKIE_AGE_YEAR);
			
			response.addCookie(idCookie);
			response.addCookie(passCookie);	
		}
		
		return "true";
	}
	
	/** 로그 아웃 */
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletResponse response){
		
		// 핸드폰에서 로그아웃 선택시 어플 로그인 데이터 삭제
		Object isPhone = session.getAttribute("isPhone");	
		if(isPhone != null && isPhone.equals("true")){
			
			Object userAttrObj = session.getAttribute("user");
			if(userAttrObj != null){
				String userId = ((UserData)userAttrObj).getUserId();
				phoneService.removeGCMData(userId);
			}		
			
			// 쿠키 삭제(유효시간을 0으로 설정)
			Cookie idCookie = new Cookie("id", null);
			Cookie passCookie = new Cookie("encPass", null);
			idCookie.setMaxAge(0);
			passCookie.setMaxAge(0);
			
			response.addCookie(idCookie);
			response.addCookie(passCookie);
		}
		session.removeAttribute("user");
		
		return "index";
	}
	
	/** 회원정보 수정 */
	@RequestMapping(value="/info/update", method=RequestMethod.POST)
	public String infoUpdate(HttpSession session, UserData data){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/noti/notiSettingjsp");
		session.setAttribute("gotoPage", "/main");
				
		loginService.update(data);
		
		throw new PageRedirectException("회원정보가 수정되었습니다.");
	}
	
	/** ID 찾기 */
	@RequestMapping(value="findId", method=RequestMethod.GET)
	public @ResponseBody String findId(@RequestParam String userName, @RequestParam String email){
		
		String result = loginService.findId(userName, email);
		
		return result == null ? "" : result;

	}
	
	/** PASSWORD 찾기 */
	@RequestMapping(value="findPass", method=RequestMethod.GET)
	public @ResponseBody String findPass(@RequestParam String userId, @RequestParam String passAnswer){
		
		return loginService.getPass(userId, passAnswer);

	}
	
	/** PASSWORD 질문 찾기 */
	@RequestMapping(value="findQuestion", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String findQuestion(@RequestParam String userId){
		
		return loginService.getQuestion(userId);

	}
}
