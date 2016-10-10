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

@Controller
public class LoginController {
	
	@Autowired
	LoginService service;	
	 
	/** 회원가입시 아이디 중복확인 */
	@RequestMapping(value="/findUser", method=RequestMethod.GET)
	public @ResponseBody String findUser(@RequestParam String userId){
		
		if(service.findUser(userId)){
			return "true";
		} else{
			return "false";
		}	
	}
	
	/** 회원가입 완료 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Model model, UserData data){
		
		data.setUserMileage(new Integer(0));
		
		if(service.join(data)){
			model.addAttribute("msg", "회원가입 되었습니다.");
			return "index";
		} else {
			model.addAttribute("msg", "오류가 발생하였습니다.\n 잠시 후 다시시도해주세요.");
			return "index";
		}
	}
	
	/** 회원정보 수정 */
	@RequestMapping(value="/info/update", method=RequestMethod.GET)
	public @ResponseBody String infoUpdate(Model model){
		
		return "true";
	}
	
	/** 로그인 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(Model model, HttpSession session,
			@RequestParam String userId, @RequestParam String userPass){
		
		UserData data = service.login(userId, userPass);
				
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
	
	/** ID 찾기 */
	@RequestMapping(value="findId", method=RequestMethod.GET)
	public @ResponseBody String findId(@RequestParam String userName, @RequestParam Integer phoneNum){
		
		String result = service.findId(userName, phoneNum);
		
		return result == null ? "" : result;

	}
	
	/** PASSWORD 찾기 */
	@RequestMapping(value="findPass", method=RequestMethod.GET)
	public @ResponseBody String findPass(@RequestParam String userId, @RequestParam String passAnswer){
		
		return service.getPass(userId, passAnswer);

	}
}
