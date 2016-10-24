package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import academy.group5.dto.UserData;
import academy.group5.service.LoginService;
import academy.group5.service.NotificationService;

/**
 * 계정 관련 컨트롤러
 * @author YSH
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;	
	 
	/** 회원가입시 아이디 중복확인 */
	@RequestMapping(value="/findUser", method=RequestMethod.GET)
	public @ResponseBody String findUser(@RequestParam String userId){
		
		if(loginService.findUser(userId)){
			return "true";
		} else{
			return "false";
		}	
	}
	
	/** 회원가입 완료 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Model model, UserData data){
		
		data.setUserMileage(new Integer(0));
		
		if(loginService.join(data) && notificationService.settingSet(data.getUserId())){
			model.addAttribute("msg", "회원가입 되었습니다.");
		} 
		return "index";
	}
	
	/** 로그인 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(Model model, HttpSession session,
			@RequestParam String userId, @RequestParam String userPass){
		
		UserData data = loginService.login(userId, userPass);
				
		if(data != null){	
			session.setAttribute("user", data);
			return "true";
		} else{
			return "false";
		}
	}
	
	/** 로그 아웃 */
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "index";
	}
	
	/** 회원정보 수정 */
	@RequestMapping(value="/info/update", method=RequestMethod.POST)
	public String infoUpdate(Model model, UserData data){
		if(loginService.update(data)){
			model.addAttribute("msg", "회원정보가 수정되었습니다.");
		}else {
			model.addAttribute("msg", "오류가 발생했습니다.\\n잠시 후 다시시도해주세요.");
		}
		return "index";
	}
	
	/** ID 찾기 */
	@RequestMapping(value="findId", method=RequestMethod.GET)
	public @ResponseBody String findId(@RequestParam String userName, @RequestParam Integer phoneNum){
		
		String result = loginService.findId(userName, phoneNum);
		
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
