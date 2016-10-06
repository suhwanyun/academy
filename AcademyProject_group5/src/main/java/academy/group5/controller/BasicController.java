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
public class BasicController {
	
	// 로그인 화면
	@RequestMapping(value="/loginjsp", method=RequestMethod.GET)
	public String loginPage(Model model){

		return "/login/login";
	}

	@RequestMapping(value="/joinjsp", method=RequestMethod.GET)
	public String joinPage(Model model){
		UserData data = new UserData();
		model.addAttribute("UserData", data);
		return "/join/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Model model, UserData data){
		
		model.addAttribute("msg", "회원가입 되었습니다.");
		return "index";
	}
	@RequestMapping(value="/searchidpw", method=RequestMethod.GET)
	public String search(Model model){
		UserData data = new UserData();
		model.addAttribute("UserData", data);
		return "/login/search_idpw";
	}
}
