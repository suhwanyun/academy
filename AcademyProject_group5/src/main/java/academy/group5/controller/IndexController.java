package academy.group5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import academy.group5.dto.Lecture;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.service.LectureService;
import academy.group5.service.LoginService;
import academy.group5.service.PostingService;

@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LectureService lecService;
	
	@Autowired
	PostingService postService;
	
	/** 메인 화면 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainPage(){

		return "/index";
	}
		
	/** 로그인 화면 */
	@RequestMapping(value="/loginjsp", method=RequestMethod.GET)
	public String loginPage(){

		return "/login/login";
	}
	
	/** 관리자 로그인 화면 */
	@RequestMapping(value="/manager_loginjsp", method=RequestMethod.GET)
	public String managerLoginPage(){

		return "/login/login_manager";
	}

	/** 회원가입 화면 */
	@RequestMapping(value="/joinjsp", method=RequestMethod.GET)
	public String joinPage(Model model){
		UserData data = new UserData();
		model.addAttribute("userData", data);
		return "/join/join";
	}

	/** 아이디 찾기, 패스워드 재설정 화면 */
	@RequestMapping(value="/searchidpw", method=RequestMethod.GET)
	public String search(){
	
		return "/login/search_idpw";
	}
	
	/** 회원정보 수정화면 */
	@RequestMapping(value="/info/myinfo", method=RequestMethod.GET)
	public String infoUpdatePage(Model model, HttpSession session){
		String id = ((UserData)session.getAttribute("user")).getUserId();
		
		UserData info = loginService.getInfo(id);
		if(info != null){
			model.addAttribute("userData", info);
			return "/info/myinfo";
		} else {
			model.addAttribute("msg", "회원정보를 가져오지 못했습니다.\n잠시 후 다시 시도해주세요");
			return "/index";
		}		
	}
	
	/** 학업 메뉴 메인 페이지 */
	@RequestMapping(value="/campus/campusMain", method=RequestMethod.GET)
	public String campusMainPage(){
		
		return "/campus/main";
	}
	
	/** 먹거리(식사) 추천 게시판 페이지 */
	@RequestMapping(value="/foodMain", method=RequestMethod.GET)
	public String foodMainPage(HttpSession session){
		List<Posting> postingList = postService.postingList(1, "food");
		session.setAttribute("postingDataList", postingList);
		session.setAttribute("postingType", "food");
		return "/food/food";
	}
	
	/** 오락 추천 게시판 페이지 */
	@RequestMapping(value="/playMain", method=RequestMethod.GET)
	public String playMainPage(){
	
		return "/play/play";
	}
	
	/** 명소 추천 게시판 페이지 */
	@RequestMapping(value="/placeMain", method=RequestMethod.GET)
	public String placeMainPage(){
	
		return "/place/place";
	}
	
	/** 마일리지 페이지 */
	@RequestMapping(value="/mileageMain", method=RequestMethod.GET)
	public String mileageMainPage(){
	
		return "/mileage/mileage";
	}
	
	/** 식사(먹거리)추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/foodjsp", method=RequestMethod.GET)
	public String addFood(){
		
		return "/food/food_add";
	}
	
	/** 오락추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/playjsp", method=RequestMethod.GET)
	public String addPlay(){

		return "/play/play_add";
	}
	
	/** 명소추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/placejsp", method=RequestMethod.GET)
	public String addPlace(){

		return "/place/place_add";
	}
	
	/** 전체 강의 목록 표시 페이지 */
	@RequestMapping(value="/campus/lectureListJsp", method=RequestMethod.GET)
	public String allLectureList(Model model, HttpSession session){
		session.removeAttribute("searchType");
		session.removeAttribute("searchData");
		
		List<Lecture> lecList = lecService.allLectureList(1, null, null);
		if(lecList.size() != 0){
			model.addAttribute("lectureList", lecList);
		}
		return "/campus/lecture_list";
	}
}
