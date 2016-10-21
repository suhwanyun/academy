package academy.group5.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.PersistenceException;
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
import academy.group5.dto.PostingComment;
import academy.group5.exception.SessionNotFoundException;
import academy.group5.service.PostingService;
import academy.group5.util.Identify;

@Controller
public class BoardController {
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	PostingService postService;
	
	Identify identify = new Identify();
	
	private final static String DEFAULT_PHOTO_NAME = "default.png";
	
	/** 식사(먹거리)추천 게시판에 글 작성 */
	@RequestMapping(value="/write/food", method=RequestMethod.POST)
	public String addFood(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
							"redirect:/foodMain", "/food/food_add", true);	
	}
	
	/** 오락추천 게시판에 글 작성 */
	@RequestMapping(value="/write/play", method=RequestMethod.POST)
	public String addPlay(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
				"redirect:/playMain", "/play/play_add", true);
	}
	
	/** 명소추천 게시판에 글 작성 */
	@RequestMapping(value="/write/place", method=RequestMethod.POST)
	public String addPlace(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
				"redirect:/placeMain", "/place/place_add", true);
	}
	
	
	
	/** 게시글 목록 표시 */
	@RequestMapping(value="/postingList", method=RequestMethod.GET)
	public @ResponseBody List<Posting> getPostingList(HttpSession session,
				@RequestParam(required=false) String page){
		
		String postingType = getPostingType(session);
		
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
		
		String postingType = getPostingType(session);
		
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

	/** 게시판 글 내용 */
	@RequestMapping(value="/postingInfo", method=RequestMethod.GET)
	public String postingInfo(Model model, HttpSession session, @RequestParam int postingId){
		String postingType = getPostingType(session);
		
		Posting postingData = postService.postView(postingId, postingType);
		model.addAttribute("postingData", postingData);
		
		// 전체 댓글 리스트(부모와 자식으로 분할됨)
		Map<String, List<PostingComment>> commentDataList = postService.commentList(postingId, postingType);
		// 부모 댓글 리스트
		List<PostingComment> parentDataList = commentDataList.get("parent");
		// 자식 댓글 리스트
		List<PostingComment> childDataList = commentDataList.get("child");
		
		// 전체 댓글 리스트를 하나의 리스트로 정렬하여 합침
		List<PostingComment> commentList = new ArrayList<>();
		int childIdx = 0;
		
		for(PostingComment parentData : parentDataList){
			int parentId = parentData.getCommentId();
			commentList.add(parentData);
			
			for(; childIdx < childDataList.size();){
				PostingComment childData = childDataList.get(childIdx);
				Integer childId = childData.getCommentParentId();
				if(childId == null){
					childIdx++;
					continue;
				}
				
				// 현재 댓글이 부모 댓글일 때
				if(childId == parentId){
					commentList.add(childData);
					childIdx++;
				}
				else{
					break;
				}
				/*// 답글의 답글 확인(자손 댓글)
				else {
					for(int grandpaIdx = parentId + 1; grandpaIdx < childDataList.size(); grandpaIdx++){
						PostingComment grandpaData = childDataList.get(grandpaIdx);
						int grandpaId = grandpaData.getCommentParentId();
						
						if(grandpaId == childId){
							commentList.add(childData);
						}
					}
				}*/
				
			}
		}
		model.addAttribute("commentList", commentList);
				
		switch(postingType){
		case "food":
			return "/food/food_info";
		case "play":
			return "/play/play_info";
		case "place":
			return "/place/place_info";
		default:
			return "/campus/lecture/lecture_board_info";
		}	
	}
	
	/** 게시판 글 수정 */
	@RequestMapping(value="/postingUpdate", method=RequestMethod.POST)
	public String postingUpdate(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest, MultipartFile uploadPhoto){
		String postingType = getPostingType(session);
		
		String okMappingStr;
		String failMappingStr;
		
		switch(postingType){
		case "food":
			okMappingStr = "/foodMain";
			failMappingStr = "/food/food_add";
		case "play":
			okMappingStr = "/playMain";
			failMappingStr = "/play/play_add";
		case "place":
			okMappingStr = "/placeMain";
			failMappingStr = "/place/place_add";
		default: // 학업 게시판, 미구현
			okMappingStr = "/index";
			failMappingStr = "/index";
		}	
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
				okMappingStr, failMappingStr, false);	
	}
	
	/** 댓글 추가 */
	@RequestMapping(value="/addComment", method=RequestMethod.POST)
	public @ResponseBody Map<String, List<PostingComment>> getCommentList(Model model, HttpSession session,
				@RequestParam Integer postingId, @RequestParam(required=false) Integer commentParentId, @RequestParam String commentContent){
		String userId = identify.getUserId(session);		
				
		String postingType = getPostingType(session);
		
		PostingComment commentData = new PostingComment(null, postingId, postingType, userId, 
														commentParentId, null, commentContent);
		try{
			if(!postService.commentWrite(commentData)){
				model.addAttribute("error", "오류가 발생하였습니다.\\n인터넷 연결을 확인하세요");
			}
		} catch(PersistenceException e){
			model.addAttribute("error", "이미 삭제된 댓글입니다.");
		}
		
		Map<String, List<PostingComment>> commentList = postService.commentList(postingId, postingType);
		
		return commentList;
	}
	
	
	/** 게시판 글 작성 로직 */
	private String addPosting(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest, MultipartFile uploadPhoto,
			String okMapping, String failMapping, boolean isNewPosting){
		
		String userId = identify.getUserId(session);
		// 에러 발생여부 플래그
		boolean isError = false;
		
		String postingType = getPostingType(session);
		// multipart/form-data 타입 form 데이터 전달
		String postingTitle = mrequest.getParameter("postingTitle");
		String postingContent = mrequest.getParameter("postingContent");
			
		Posting postingData = new Posting(postingType, userId, postingTitle, postingContent, DEFAULT_PHOTO_NAME);
		
		if(isError || postingType == null || postingTitle == null || postingContent == null) {
			throw new SessionNotFoundException();	
		} else if(postingTitle.equals("")){
			model.addAttribute("msg", "제목을 입력해주세요.");
			isError = true;
		} else if(postingContent.equals("")){
			model.addAttribute("msg", "내용을 입력해주세요.");
			isError = true;
		}

		if(!isError && ((isNewPosting && postService.postWrite(postingData)) ||
						(!isNewPosting && postService.postModify(postingData)))
			){
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
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.\\n잠시 후 다시 시도해주세요.");
		}

		// 에러가 발생하여 작성화면으로 돌아가기
		model.addAttribute("posting", postingData);
		return failMapping;
	}
	
	/** 게시판 종류 확인 */
	private String getPostingType(HttpSession session){
		Object postingTypeObj = session.getAttribute("postingType");
		String postingType = null;
		
		if(postingTypeObj != null){
			postingType = (String)postingTypeObj;
		} else {
			throw new SessionNotFoundException();
		}
		
		return postingType;
	}
	
}
