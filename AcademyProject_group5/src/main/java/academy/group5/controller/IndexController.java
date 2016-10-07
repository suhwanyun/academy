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
	
	// 메인 화면
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainPage(){

		return "index";
	}
		
	// 로그인 화면
	@RequestMapping(value="/loginjsp", method=RequestMethod.GET)
	public String loginPage(){

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
	public String search(){
	
		return "/login/search_idpw";
	}
	
	// 학업 메뉴 메인 페이지
	@RequestMapping(value="/campusMain", method=RequestMethod.GET)
	public String campusMainPage(){
		
		return "campus/main";
	}
	
	// 먹거리(식사) 추천 게시판 페이지
	@RequestMapping(value="/foodMain", method=RequestMethod.GET)
	public String foodMainPage(){
	
		return "/food/food";
	}
	
	// 오락 추천 게시판 페이지
	@RequestMapping(value="/playMain", method=RequestMethod.GET)
	public String playMainPage(){
	
		return "/play/play";
	}
	
	// 명소 추천 게시판 페이지
	@RequestMapping(value="/placeMain", method=RequestMethod.GET)
	public String placeMainPage(){
	
		return "/place/place";
	}
	
	// 마일리지 페이지
	@RequestMapping(value="/mileageMain", method=RequestMethod.GET)
	public String mileageMainPage(){
	
		return "/mileage/mileage";
	}
}
