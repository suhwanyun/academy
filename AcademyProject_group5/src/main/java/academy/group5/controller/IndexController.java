package academy.group5.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.dto.MileageProduct;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.LectureNoticeSetTime;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.LectureNoticeService;
import academy.group5.service.LectureService;
import academy.group5.service.LoginService;
import academy.group5.service.ManagerService;
import academy.group5.service.PostingService;
import academy.group5.util.Identify;
import academy.group5.util.MyHash;

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
	LectureNoticeService lecNotiService;
	
	@Autowired
	PostingService postService;
	
	@Autowired
	ManagerService manageService;
	
	Identify identify = new Identify();
	
	/** 메인 화면 */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainPage(HttpSession session,
			@CookieValue(required=false, value="id") String userId,
			@CookieValue(required=false, value="encPass") String encPass){
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/main");
		session.setAttribute("gotoPage", "/main");
		// 학업 페이지에서 열렸던 텝 기록 삭제
		session.removeAttribute("nowTab");
		// 자동 로그인 설정
		if(session.getAttribute("user") == null
				&& userId != null && encPass != null){
			UserData data = loginService.autoLogin(userId, encPass);
			session.setAttribute("user", data);
		}
		
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
	
	// -----------------------메뉴 메인 페이지----------------------- */
	
	/** 학업 메뉴 메인 페이지 */
	@RequestMapping(value="/campus/campusMain", method=RequestMethod.GET)
	public String campusMainPage(HttpSession session){
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/main");
		// 이전에 더보기를 하거나, 강의 정보를 확인했을 때 설정했던 정보 삭제
		session.removeAttribute("page");
		session.removeAttribute("lectureId");
		session.removeAttribute("lectureClass");
		session.removeAttribute("isPresident");
		session.removeAttribute("lectureName");
		
		return "/campus/main";
	}
	
	/** 먹거리(식사) 추천 게시판 페이지 */
	@RequestMapping(value="/foodMain", method=RequestMethod.GET)
	public String foodMainPage(HttpSession session){
		boardMainSetup(session, Posting.TYPE_FOOD, MostRecommend.PERIOD_DAY);
		return "/food/food";
	}
	
	/** 오락 추천 게시판 페이지 */
	@RequestMapping(value="/playMain", method=RequestMethod.GET)
	public String playMainPage(HttpSession session){
		boardMainSetup(session, Posting.TYPE_PLAY, MostRecommend.PERIOD_DAY);
		return "/play/play";
	}
	
	/** 명소 추천 게시판 페이지 */
	@RequestMapping(value="/placeMain", method=RequestMethod.GET)
	public String placeMainPage(HttpSession session){
		boardMainSetup(session, Posting.TYPE_PLACE, MostRecommend.PERIOD_WEEK);
		return "/place/place";
	}
	
	/** 학생이 선택한 강의의 메인 페이지 */
	@RequestMapping(value="/lecture/lectureMain", method=RequestMethod.GET)
	public String lectureMainPage(HttpSession session, Model model,
			@RequestParam Integer lectureId, @RequestParam Integer lectureClass){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		// 현재 열린 탭 저장
		session.setAttribute("nowTab", "selectedLectureList");
		// 현재 선택된 강의 정보 저장
		session.setAttribute("lectureId", lectureId);
		session.setAttribute("lectureClass", lectureClass);
		session.setAttribute("lectureName", lecService.getLectureName(lectureId, lectureClass));
				
		String userId = identify.getUserId(session);
		if(!lecService.isAppliedLecture(lectureId, userId, lectureClass)){
			throw new WrongRequestException("신청하지 않은 강의의 정보는 확인하실 수 없습니다.");
		}else if(lecService.getIsPresident(lectureId, userId, lectureClass)){
			session.setAttribute("isPresident", "Y");
		} else {
			session.setAttribute("isPresident", "N");
		}
		String postingType = Posting.TYPE_LECTURE + "_" + lectureId + "_" + lectureClass;
		// 게시판 초기화
		boardMainSetup(session, postingType, null);
		// 공지 목록
		List<LectureNotice> noticeList = lecNotiService.lectureNoticeList(lectureId, lectureClass, 1);
		if(noticeList.size() != 0){
			model.addAttribute("noticeList", noticeList);
		}
		
		return "/campus/lecture/lecture_main";
	}
	
	/** 마일리지 페이지 */
	@RequestMapping(value="/mileageMain", method=RequestMethod.GET)
	public String mileageMainPage(){
		
		return "/mileage/mileage";
	}
	
	/** 강의등록 관리자 메인 페이지 */
	@RequestMapping(value="/lectureManage/main", method=RequestMethod.GET)
	public String manageLectureMainPage(HttpSession session, Model model){
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/termSettingjsp");
		// 학기 설정 여부 확인
		manageService.isTermSetted();
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/managerLoginjsp");
		// 기존 검색 기록 초기화
		session.removeAttribute("searchData");
		session.removeAttribute("searchType");
		
		List<Lecture> lectureList = manageService.getAllLectureList();
		
		model.addAttribute("lectureList", lectureList);	
		model.addAttribute("pageCount", manageService.getMaxLectureListPage());
		// 이후 페이지에서 에러 발생시 이동할 페이지를 현재 페이지로 설정
		session.setAttribute("errorGotoPage", "/lectureManage/main");
		return "/manage/lecture/lecture";
	}
	
	/** 마일리지 등록 관리자 메인 페이지 */
	@RequestMapping(value="/mileageManage/main", method=RequestMethod.GET)
	public String manageMileageMainPage(HttpSession session, Model model){
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/managerLoginjsp");
		// 마일리지 물품 리스트
		List<MileageProduct> productList = manageService.getAllProduct(1, null, true);
		model.addAttribute("productList", productList);
		// 최대 페이지수
		session.setAttribute("maxPage", manageService.getMaxMileagePage());
		// 현재 페이지 초기화
		session.setAttribute("page", 1);
		
		// 이후 페이지에서 에러 발생시 이동할 페이지를 현재 페이지로 설정
		session.setAttribute("errorGotoPage", "/mileageManage/main");
				
		return "/manage/mileage/mileage";
	}
	
	// -----------------------게시판 페이지 연결----------------------- */
	
	/** 식사(먹거리)추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/foodjsp", method=RequestMethod.GET)
	public String addFoodPostingPage(HttpSession session, Model model){	
		setPrevPostingData(session, model, null);
		return "/food/food_add";
	}
	
	/** 오락추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/playjsp", method=RequestMethod.GET)
	public String addPlayPostingPage(HttpSession session, Model model){
		setPrevPostingData(session, model, null);
		return "/play/play_add";
	}
	
	/** 명소추천 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/placejsp", method=RequestMethod.GET)
	public String addPlacePostingPage(HttpSession session, Model model){
		setPrevPostingData(session, model, null);
		return "/place/place_add";
	}
	
	/** 강의 게시판 글 작성 페이지 */
	@RequestMapping(value="/write/lecturejsp", method=RequestMethod.GET)
	public String addLecturePostingPage(HttpSession session, Model model){
		setPrevPostingData(session, model, null);
		return "/campus/lecture/lecture_board_add";
	}
	
	/** 강의 알림 등록 페이지 */
	@RequestMapping(value="/write/lectureNotiAddjsp", method=RequestMethod.GET)
	public String lectureNotiAddPage(HttpSession session, Model model){
		
		Object idObj = session.getAttribute("lectureId");
		Object classObj = session.getAttribute("lectureClass");
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		if(idObj != null && classObj != null){
			session.setAttribute("errorGotoPage", "/lecture/lectureMain?lectureId="+idObj+"&lectureClass="+classObj);
		} else {
			session.setAttribute("errorGotoPage", "/campus/campusMain");
			throw new WrongRequestException();
		}
		Integer lectureId = (Integer)idObj;
		Integer lectureClass = (Integer)classObj;
		
		String userId = identify.getUserId(session);
		
		if(!lecService.getIsPresident(lectureId, userId, lectureClass)){
			throw new WrongRequestException("반장만 등록할 수 있습니다.");
		}		
		return "/campus/lecture/lecture_noti_add";
	}
	
	/** 강의 시간 변경 페이지(일시적인) */
	@RequestMapping(value="/write/lectureTimeNotiAddjsp", method=RequestMethod.GET)
	public String lectureUpdatePage(HttpSession session, Model model,
			@RequestParam Integer lectureTimeId){
	
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		// 현재 열린 탭 저장
		session.setAttribute("nowTab", "selectedLectureList");
	
		LectureTime timeData = lecService.getLectureTimeById(lectureTimeId);
		session.setAttribute("lectureName", lecService.getLectureName(timeData.getLectureId(), timeData.getLectureClass()));
		
		String userId = identify.getUserId(session);
		int lectureId = timeData.getLectureId();
		int lectureClass = timeData.getLectureClass();
		
		if(!lecService.getIsPresident(lectureId, userId, lectureClass)){
			throw new WrongRequestException("반장만 등록할 수 있습니다.");
		}
		// 변경될 강의의 정보
		model.addAttribute("lectureTimeSetting", new LectureNoticeSetTime(timeData));
		// 변경될 강의의 다음 강의 시간 텍스트(출력용)
		String timeStr = lecService.getLectureTimeStr(timeData);
		model.addAttribute("targetTimeStr", timeStr);
		model.addAttribute("targetTimeStrWithoutDate", timeStr.substring(7));
		
		return "/campus/lecture/lecture_update";
	}
	
	/** 식사(먹거리)추천 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/foodUpdatejsp", method=RequestMethod.GET)
	public String updateFoodPostingPage(Model model, HttpSession session,
								@RequestParam int postingId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/foodMain");

		getPostingData(model, session, postingId);
		
		return "/food/food_update";	
	}
	
	/** 오락추천 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/playUpdatejsp", method=RequestMethod.GET)
	public String updatePlayPostingPage(Model model, HttpSession session,
			@RequestParam int postingId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/playMain");
		
		getPostingData(model, session, postingId);
		
		return "/play/play_update";	
	}
	
	/** 명소추천 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/placeUpdatejsp", method=RequestMethod.GET)
	public String updatePlacePostingPage(Model model, HttpSession session, 
			@RequestParam int postingId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/placeMain");

		getPostingData(model, session, postingId);
		
		return "/place/place_update";
	}	
	
	/** 강의 게시판 글 수정 페이지 */
	@RequestMapping(value="/write/lectureUpdatejsp", method=RequestMethod.GET)
	public String updateLecturePostingPage(Model model, HttpSession session, 
			@RequestParam int postingId){
		
		Object idObj = session.getAttribute("lectureId");
		Object classObj = session.getAttribute("lectureClass");
		// 에러 발생시 이동할 페이지
		if(idObj != null && classObj != null){
			session.setAttribute("errorGotoPage", "/lecture/lectureMain?lectureId="+idObj+"&lectureClass="+classObj);
		} else {
			session.setAttribute("errorGotoPage", "/campus/campusMain");
		}
		getPostingData(model, session, postingId);
		
		return "/campus/lecture/lecture_update";
	}	
	
	/** 게시판 메인 페이지 초기화 설정 */
	private void boardMainSetup(HttpSession session, String postingType, Integer recommendPeriod){
		session.removeAttribute("mostRecommendData");	
		// 제일 추천을 많이 받은 게시글은 따로 전달(강의 게시판 제외)
		if(recommendPeriod != null){
			Posting mostRecommendData = postService.mostRecommend(
					new MostRecommend(postingType, recommendPeriod));
			
			if(mostRecommendData != null){
				session.setAttribute("mostRecommendData", mostRecommendData);
			}
		}
		
		List<Posting> postingList = postService.postingList(1, postingType);
		session.setAttribute("postingDataList", postingList);
		session.setAttribute("postingType", postingType);
		
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
		postService.replaceFromBr(postingData); 
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
	
	//------------------------------관리자(강의)------------------------------ */
	
	/** 학기 추가 페이지*/
	@RequestMapping(value="/lectureManage/termSettingjsp", method=RequestMethod.GET)
	public String termSettingPage(HttpSession session){		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/main");
				
		// 학기 설정이 되어있으면 에러발생
		manageService.isNotTermSetted();
		return "/manage/term_setting";
	}
	
	/** 관리자 로그인 화면 */
	@RequestMapping(value="/managerLoginjsp", method=RequestMethod.GET)
	public String managerLoginPage(HttpSession session){
		
		// 매니저 접속임을 명시
		session.setAttribute("isManage", "true");
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/managerLoginjsp");
				
		Object typeObj = session.getAttribute("managerType");
		if(typeObj != null && typeObj.equals("lecture")){
			return "redirect:/lectureManage/main";
		} else if(typeObj != null && typeObj.equals("mileage")){
			return "redirect:/mileageManage/main";
		}
		
		return "/login/login_manager";
	}
	
	/** 강의 등록 페이지 */
	@RequestMapping(value="/lectureManage/addjsp", method=RequestMethod.GET)
	public String addLecturePage(){
		return "manage/lecture/lecture_add";
	}
	
	/** 강의 관리 페이지 */
	@RequestMapping(value="/lectureManage/managejsp", method=RequestMethod.GET)
	public String manageLecture(HttpSession session, Model model,
			@RequestParam int lectureId, @RequestParam int lectureClass){
				
		Lecture lectureData = manageService.getLecture(lectureId, lectureClass);
		model.addAttribute("lectureData", lectureData);
		return "/manage/lecture/manage";
	}
	
	/** 강의 시간 등록 페이지 */
	@RequestMapping(value="/lectureManage/timeAddjsp", method=RequestMethod.GET)
	public String addLectureTime(HttpSession session, Model model,
			@RequestParam Integer lectureId, @RequestParam Integer lectureClass){
		
		LectureTime timeData = new LectureTime(lectureId, lectureClass);
		model.addAttribute("timeData", timeData);
		return "/manage/lecture/lecture_time_add";
	}
	
	/** 강의 시간 관리 페이지 */
	@RequestMapping(value="/lectureManage/timeManagejsp", method=RequestMethod.GET)
	public String manageLectureTime(HttpSession session, Model model,
			@RequestParam int lectureTimeId){
		
		LectureTime timeData = manageService.getLectureTime(lectureTimeId);
		model.addAttribute("timeData", timeData);
		return "/manage/lecture/lecture_time_manage";
	}
	
	//------------------------------관리자(마일리지)------------------------------ */
	
	/** 마일리지 물품 등록 페이지 */
	@RequestMapping(value="/mileageManage/addjsp", method=RequestMethod.GET)
	public String addMileage(){
		
		return "/manage/mileage/add";
	}
	
	/** 마일리지 물품 관리 페이지 */
	@RequestMapping(value="/mileageManage/managejsp", method=RequestMethod.GET)
	public String manageMileage(HttpSession session, Model model,
			@RequestParam int productId){
		
		// 에러 발생시 / 작업 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/mileageManage/main");
		session.setAttribute("gotoPage", "/main");
				
		MileageProduct productData = manageService.getProduct(productId);
		model.addAttribute("productData", productData);
		
		return "/manage/mileage/manage";
	}
}
