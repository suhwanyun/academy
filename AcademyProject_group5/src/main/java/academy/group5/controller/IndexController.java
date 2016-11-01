package academy.group5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import academy.group5.dto.Lecture;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.LectureService;
import academy.group5.service.LoginService;
import academy.group5.service.ManagerService;
import academy.group5.service.PostingService;
import academy.group5.util.Identify;

/**
 * 페이지(JSP) 이동 컨트롤러
 * @author YSH
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LectureService lecService;
	
	@Autowired
	PostingService postService;
	
	Identify identify = new Identify();
	
	/** 메인 화면 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainPage(HttpSession session){
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/main");
		session.setAttribute("gotoPage", "/main");
		return "/index";
	}
	
	/** 메세지 표시 화면 */
	@RequestMapping(value="/message", method=RequestMethod.GET)
	public String messagePage(){
		
		return "message";
	}
		
	/** 로그인 화면 */
	@RequestMapping(value="/loginjsp", method=RequestMethod.GET)
	public String loginPage(HttpSession session){
		Object loginObj = session.getAttribute("user");
		if(loginObj != null){
			session.setAttribute("gotoPage", "/main");
			throw new PageRedirectException();
		} 
		
		return "login/login";
	}
	
	/** 회원가입 화면 */
	@RequestMapping(value="/joinjsp", method=RequestMethod.GET)
	public String joinPage(Model model){
		UserData data = new UserData();
		data.setPassQuestion(UserData.DEFAULT_PASS_QUESTION);
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
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/main");
				
		String id = identify.getUserId(session);
		UserData info = loginService.getInfo(id);
		
		model.addAttribute("userData", info);
		return "/info/myinfo";		
	}
	
	/** 학업 메뉴 메인 페이지 */
	@RequestMapping(value="/campus/campusMain", method=RequestMethod.GET)
	public String campusMainPage(){
		
		return "/campus/main";
	}
	
	/** 먹거리(식사) 추천 게시판 페이지 */
	@RequestMapping(value="/foodMain", method=RequestMethod.GET)
	public String foodMainPage(HttpSession session){
		boardMainSetup(session, "food");
		return "/food/food";
	}
	
	/** 오락 추천 게시판 페이지 */
	@RequestMapping(value="/playMain", method=RequestMethod.GET)
	public String playMainPage(HttpSession session){
		boardMainSetup(session, "play");
		return "/play/play";
	}
	
	/** 명소 추천 게시판 페이지 */
	@RequestMapping(value="/placeMain", method=RequestMethod.GET)
	public String placeMainPage(HttpSession session){
		boardMainSetup(session, "place");
		return "/place/place";
	}
	
	/** 마일리지 페이지 */
	@RequestMapping(value="/mileageMain", method=RequestMethod.GET)
	public String mileageMainPage(){
	
		return "/mileage/mileage";
	}
	
	
	/** 식사(먹거리)추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/foodjsp", method=RequestMethod.GET)
	public String addFood(HttpSession session, Model model){	
		setPrevPostingData(session, model, null);
		return "/food/food_add";
	}
	
	/** 오락추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/playjsp", method=RequestMethod.GET)
	public String addPlay(HttpSession session, Model model){
		setPrevPostingData(session, model, null);
		return "/play/play_add";
	}
	
	/** 명소추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/placejsp", method=RequestMethod.GET)
	public String addPlace(HttpSession session, Model model){
		setPrevPostingData(session, model, null);
		return "/place/place_add";
	}
	
	
	/** 식사(먹거리)추천 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/foodUpdatejsp", method=RequestMethod.GET)
	public String updateFood(Model model, HttpSession session,
								@RequestParam int postingId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/foodMain");

		getPostingData(model, session, postingId);
		
		return "/food/food_update";	
	}
	
	/** 오락추천 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/playUpdatejsp", method=RequestMethod.GET)
	public String updatePlay(Model model, HttpSession session,
			@RequestParam int postingId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/playMain");
		
		getPostingData(model, session, postingId);
		
		return "/play/play_update";	
	}
	
	/** 명소추천 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/placeUpdatejsp", method=RequestMethod.GET)
	public String updatePlace(Model model, HttpSession session, 
			@RequestParam int postingId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/placeMain");

		getPostingData(model, session, postingId);
		
		return "/place/place_update";
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
	
	
	/** 게시판 메인 페이지 초기화 설정 */
	private void boardMainSetup(HttpSession session, String PostingType){
		Posting mostRecommendData = postService.mostRecommend(
				new MostRecommend(PostingType, MostRecommend.PERIOD_DAY));
		
		if(mostRecommendData != null){
			session.setAttribute("mostRecommendData", mostRecommendData);
		} else {
			session.removeAttribute("mostRecommendData");
		}
		
		List<Posting> postingList = postService.postingList(1, PostingType);
		session.setAttribute("postingDataList", postingList);
		session.setAttribute("postingType", PostingType);
		
		session.removeAttribute("searchType");
		session.removeAttribute("searchData");
		session.removeAttribute("orderData");
	}
	
	/** 게시판 종류 확인 */
	private String getPostingType(HttpSession session){
		Object postingTypeObj = session.getAttribute("postingType");
		String postingType = null;
		
		if(postingTypeObj != null){
			postingType = (String)postingTypeObj;
		} else {
			throw new WrongRequestException();
		}
		
		return postingType;
	}
	
	/** 게시판 글 수정시, 글 정보 가져오기 */
	private boolean getPostingData(Model model, HttpSession session, int postingId){
		String userId = identify.getUserId(session);
		String postingType = getPostingType(session);
		Posting postingData = postService.postView(postingId, postingType);
		
		// 본인이 작성한 글이 아니면
		if(!userId.equals(postingData.getUserId())){
			throw new WrongRequestException();
		}
		
		setPrevPostingData(session, model, postingData);	
		model.addAttribute("postingData", postingData);
		return true;
	}
	
	/** 게시글 작성시, 백업 데이터가 존재할 경우 DB 데이터보다 우선 설정 */
	private void setPrevPostingData(HttpSession session, Model model, Posting originalData){
		Object prevPostingObj = session.getAttribute("postingData");
		Posting prevPostingData = prevPostingObj != null ? (Posting)prevPostingObj : null;
		
		if(prevPostingData != null){
			session.removeAttribute("postingData");
			
			if(originalData != null){
				originalData.setPostingTitle(prevPostingData.getPostingTitle());
				originalData.setPostingContent(prevPostingData.getPostingContent());
			} else {
				model.addAttribute("postingData", prevPostingData);
			}
		}
	}
	
	/**-----------------------관리자----------------------- */
	
	/** 관리자 로그인 화면 */
	@RequestMapping(value="/managerLoginjsp", method=RequestMethod.GET)
	public String managerLoginPage(HttpSession session){
		
		session.setAttribute("isManage", "true");
		
		Object loginObj = session.getAttribute("managerType");
		if(loginObj != null){
			if(loginObj.equals(ManagerService.TYPE_LECTURE)){
				session.setAttribute("gotoPage", "/lectureManage/main");
			} else {
				session.setAttribute("gotoPage", "/mileageManage/main");
			}
			
			throw new PageRedirectException();
		}
		return "/login/login_manager";
	}
	
	/** 강의 등록 페이지 */
	@RequestMapping(value="/lectureManage/addjsp", method=RequestMethod.GET)
	public String addLecturePage(){
		return "manage/lecture_add";
	}
}
