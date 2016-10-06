package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import academy.group5.dto.UserData;

@Controller
public class IndexController {
	
	// 로그인 화면
	@RequestMapping(value="/loginjsp", method=RequestMethod.GET)
	public String loginPage(Model model){

		return "/login/login";
	}

	// 회원가입 화면
	@RequestMapping(value="/joinjsp", method=RequestMethod.GET)
	public String joinPage(Model model){
		UserData data = new UserData();
		model.addAttribute("UserData", data);
		return "/join/join";
	}

	// 아이디 찾기, 패스워드 재설정 화면
	@RequestMapping(value="/searchidpw", method=RequestMethod.GET)
	public String search(Model model){
	
		return "/login/search_idpw";
	}
}
