package academy.group5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.service.PostingService;

@Controller
public class BoardController {
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	PostingService postService;
	
	private final static String DEFAULT_PHOTO_NAME = "default.png";
	
	/** 식사(먹거리)추천 게시판에 글 작성 */
	@RequestMapping(value="/write/food", method=RequestMethod.POST)
	public String addFood(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
							"redirect:/foodMain", "/food/food_add");	
	}
	
	/** 오락추천 게시판에 글 작성 */
	@RequestMapping(value="/write/play", method=RequestMethod.POST)
	public String addPlay(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
				"redirect:/playMain", "/play/play_add");
	}
	
	/** 명소추천 게시판에 글 작성 */
	@RequestMapping(value="/write/place", method=RequestMethod.POST)
	public String addPlace(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
				"redirect:/placeMain", "/place/place_add");
	}
	
	/** 게시판 글 작성 로직 */
	private String addPosting(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest, MultipartFile uploadPhoto,
			String okMapping, String failMapping){
		
		String userId = null;
		// 에러 발생여부 플래그
		boolean isError = false;
			
		// 로그인된 id 확인
		Object userAttrObj = session.getAttribute("user");
		if(userAttrObj != null){
			userId = ((UserData)userAttrObj).getUserId();
		}else {		
			isError = true;		
		}
		// multipart/form-data 타입 form 데이터 전달
		String postingType = mrequest.getParameter("postingType");
		String postingTitle = mrequest.getParameter("postingTitle");
		String postingContent = mrequest.getParameter("postingContent");
			
		Posting postingData = new Posting(postingType, userId, postingTitle, postingContent, DEFAULT_PHOTO_NAME);
		
		if(isError || postingType == null || postingTitle == null || postingContent == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			isError = true;		
		} else if(postingTitle.equals("")){
			model.addAttribute("msg", "제목을 입력해주세요.");
			isError = true;
		} else if(postingContent.equals("")){
			model.addAttribute("msg", "내용을 입력해주세요.");
			isError = true;
		}

		if(!isError && postService.postWrite(postingData)){
			// 이미지 업로드 처리
			if(!uploadPhoto.isEmpty()){
				int uploadResult = postService.upload(uploadPhoto, postingData);
				
				// 이미지 업로드 실패시 처리
				if(uploadResult == -1){
					redAttr.addFlashAttribute("msg", "이미지 업로드에 실패하였습니다.");
					return okMapping;
				}
			}
			/* 정상 처리 */
			redAttr.addFlashAttribute("msg", "등록되었습니다.");
			return okMapping;
		} else if(!isError){
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.\\n인터넷 연결을 확인하세요");
		}

		// 에러가 발생하여 작성화면으로 돌아가기
		model.addAttribute("posting", postingData);
		return failMapping;
	}
	
	/** 게시글 목록 표시 */
	@RequestMapping(value="/postingList", method=RequestMethod.GET)
	public @ResponseBody List<Posting> getPostingList(HttpSession session,
				@RequestParam(required=false) String page){
		
		String postingType = (String)session.getAttribute("postingType");
		
		Object dataObj = session.getAttribute("searchData");
		Object typeObj = session.getAttribute("searchType");
		Object orderObj = session.getAttribute("orderData");
		String searchData = dataObj == null ? null : (String)dataObj;
		String searchType = dataObj == null ? null : (String)typeObj;
		String orderData = orderObj == null ? null : (String)orderObj;
		
		List<Posting> postingList = new ArrayList<>();
		
		int nowPage = page == null ? 1 : Integer.parseInt(page);
		// 게시글 목록 출력
		postingList.addAll(postService.postingList(nowPage, postingType, searchData, searchType, orderData));

		return postingList;
	}
	
	/** 식사(먹거리)추천 게시판 검색 */
	@RequestMapping(value="/postingSearch", method=RequestMethod.GET)
	public @ResponseBody List<Posting> setSearchDataForGetlectureList(Model model, HttpSession session,
			@RequestParam String searchType, @RequestParam String searchData, 
			@RequestParam String orderData){
		
		String postingType = (String)session.getAttribute("postingType");
		
		session.setAttribute("orderData", orderData);
		
		if(searchType.equals("") || searchData.equals("")){
			session.removeAttribute("searchType");
			session.removeAttribute("searchData");
			
			return postService.postingList(1, postingType, null, null, orderData);
		} else {
			session.setAttribute("searchType", searchType);
			session.setAttribute("searchData", searchData);
			
			return postService.postingList(1, postingType, searchData, searchType, orderData);
		}
	}

	/** 식사(먹거리)추천 게시판 글 내용 */
	@RequestMapping(value="/food_info", method=RequestMethod.GET)
	public String foodInfo(){
		
		return "/food/food_info";
	}
	
	/** 오락추천 게시판 글 내용 */
	@RequestMapping(value="/play_info", method=RequestMethod.GET)
	public String playInfo(){
		
		return "/play/play_info";
	}
	
	/** 오락추천 게시판 글 내용 */
	@RequestMapping(value="/place_info", method=RequestMethod.GET)
	public String placeInfo(){
		
		return "/place/place_info";
	}
	
}
