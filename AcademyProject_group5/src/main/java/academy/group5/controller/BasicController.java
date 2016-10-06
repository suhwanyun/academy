package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import academy.group5.dto.UserData;

@Controller
public class BasicController {
	
	// 로그인 화면
	@RequestMapping(value="/loginjsp", method=RequestMethod.GET)
	public String toLogin(Model model){
		
		//model.addAttribute("message", "Good Morning");
		return "/login/login";
	}

	@RequestMapping(value="/joinjsp", method=RequestMethod.GET)
	public String toJoin(Model model, UserData data){
		
		//model.addAttribute("message", "Good Morning");
		return "/join/join";
	}
}
