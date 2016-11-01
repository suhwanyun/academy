package academy.group5.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import academy.group5.dto.PostingComment;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.PostingService;
import academy.group5.util.Identify;

/**
 * 게시판 컨트롤러
 * @author YSH
 * 
 */
@Controller
public class BoardController {
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	PostingService postService;
	
	Identify identify = new Identify();
	
	private final String BOARD_TYPE_FOOD = "food";
	private final String BOARD_TYPE_PLAY = "play";
	private final String BOARD_TYPE_PLACE = "place";
	
	/** 식사(먹거리)추천 게시판에 글 작성 */
	@RequestMapping(value="/write/food", method=RequestMethod.POST)
	public String addFood(Model model, HttpSession session,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, mrequest, uploadPhoto,
							"/write/foodjsp", true);	
	}
	
	/** 오락추천 게시판에 글 작성 */
	@RequestMapping(value="/write/play", method=RequestMethod.POST)
	public String addPlay(Model model, HttpSession session,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		return addPosting(model, session, mrequest, uploadPhoto,
				"/write/playjsp", true);
	}
	
	/** 명소추천 게시판에 글 작성 */
	@RequestMapping(value="/write/place", method=RequestMethod.POST)
	public String addPlace(Model model, HttpSession session,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
				
		return addPosting(model, session, mrequest, uploadPhoto,
				"/write/placejsp", true);
	}
	

	
	/** 게시글 목록 표시 */
	@RequestMapping(value="/postingList", method=RequestMethod.GET)
	public @ResponseBody List<Posting> getPostingList(HttpSession session,
				@RequestParam(required=false) String page){
		
		String postingType = setRedirectPage(session);
		
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
	
	/** 게시판 정렬 */
	@RequestMapping(value="/postingOrder", method=RequestMethod.GET)
	public @ResponseBody List<Posting> postingOrder(Model model, HttpSession session,
			@RequestParam String orderData){
		
		String postingType = setRedirectPage(session);
		
		session.setAttribute("orderData", orderData);

		Object searchObj = session.getAttribute("searchData");
		Object typeObj = session.getAttribute("searchType");
		
		String searchData = searchObj == null ? null : (String)searchObj;
		String searchType = typeObj == null ? null : (String)typeObj;
		
		return postService.postingList(1, postingType, searchData, searchType, orderData);
		
	}
	
	/** 게시판 검색 */
	@RequestMapping(value="/postingSearch", method=RequestMethod.GET)
	public @ResponseBody List<Posting> postingSearch(Model model, HttpSession session,
			@RequestParam String searchType, @RequestParam String searchData, 
			@RequestParam String orderData){
		
		String postingType = setRedirectPage(session);
		
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
	public String postingInfo(Model model, HttpSession session, 
			@RequestParam int postingId){
		String postingType = setRedirectPage(session);
		
		Posting postingData = postService.postView(postingId, postingType);
		model.addAttribute("postingData", postingData);
		
		// 전체 댓글 리스트(부모와 자식으로 분할됨)
		Map<String, List<PostingComment>> commentDataList = postService.commentList(postingId, postingType);
		// Map -> List로 변환
		List<PostingComment> mergedCommentList = mergeComment(commentDataList.get("parent"), commentDataList.get("child"));
		
		model.addAttribute("commentList", mergedCommentList);
		
		switch(postingType){
		case BOARD_TYPE_FOOD:
			return "/food/food_info";
		case BOARD_TYPE_PLAY:
			return "/play/play_info";
		case BOARD_TYPE_PLACE:
			return "/place/place_info";
		default:
			return "/campus/lecture/lecture_board_info";
		}	
	}
	
	/** 게시판 글 수정 */
	@RequestMapping(value="/write/postingUpdate", method=RequestMethod.POST)
	public String postingUpdate(Model model, HttpSession session,
			MultipartHttpServletRequest mrequest, @RequestParam(required=false) MultipartFile uploadPhoto){
		String postingType = setRedirectPage(session);
		String failMappingStr;
		
		String postingId = mrequest.getParameter("postingId");
		// 사용자 입력 오류시 복구할 페이지 설정
		switch(postingType){
		case BOARD_TYPE_FOOD:	
			failMappingStr = "/write/foodUpdatejsp?postingId=" + postingId;
			break;
		case BOARD_TYPE_PLAY:
			// 사용자 입력 오류시 복구할 페이지
			failMappingStr = "/write/playUpdatejsp?postingId=" + postingId;
			break;
		case BOARD_TYPE_PLACE:
			// 사용자 입력 오류시 복구할 페이지
			failMappingStr = "/write/placeUpdatejsp?postingId=" + postingId;
			break;
		default: // 학업 게시판, 미구현
			// 사용자 입력 오류시 복구할 페이지
			failMappingStr = "/index";
			break;
		}
		
		return addPosting(model, session, mrequest, uploadPhoto,
										 failMappingStr, false);	
	}
	
	/** 게시글 삭제 */
	@RequestMapping(value="/write/postingDelete", method=RequestMethod.GET)
	public String deletePosting(HttpSession session, @RequestParam Integer postingId){
		
		String postingType = setRedirectPage(session);	
		String userId = identify.getUserId(session);
		// 게시글 삭제
		if(postService.postDelete(userId, postingId, postingType)){
			throw new PageRedirectException("삭제되었습니다");
		} 	
		
		return "index";
	}
	
	/** 댓글 추가 */
	@RequestMapping(value="/write/addComment", method=RequestMethod.POST)
	public @ResponseBody Map<String, List<PostingComment>> getCommentList(Model model, HttpSession session,
				@RequestParam Integer postingId, @RequestParam(required=false) Integer commentParentId, @RequestParam String commentContent){
		
		String postingType = setRedirectPage(session);
		String userId = identify.getUserId(session);		
		
		PostingComment commentData = new PostingComment(null, postingId, postingType, userId, 
														commentParentId, null, commentContent);		
		postService.commentWrite(commentData);	
		
		Map<String, List<PostingComment>> commentList = postService.commentList(postingId, postingType);
		
		return commentList;
	}
	
	/** 댓글 수정 */
	@RequestMapping(value="/write/updateComment", method=RequestMethod.POST)
	public @ResponseBody Map<String, List<PostingComment>> updateComment(Model model, HttpSession session,
			@RequestParam Integer commentId, @RequestParam Integer postingId, @RequestParam String commentContent){
		
		String postingType = setRedirectPage(session);
		String userId = identify.getUserId(session);	
		
		PostingComment commentData = new PostingComment(commentId, null, null, userId, 
														null, null, commentContent);
		postService.commentModify(commentData);
		
		Map<String, List<PostingComment>> commentList = postService.commentList(postingId, postingType);
		
		return commentList;
	}
	
	/** 
	 * 댓글 삭제 
	 * sendmsg : postingInfo 에 전달하는 메세지
	 */
	@RequestMapping(value="/write/deleteComment", method=RequestMethod.GET)
	public String deleteComment(HttpSession session,
			@RequestParam Integer postingId, @RequestParam Integer commentId){
		
		setRedirectInfoPage(session, postingId);
		if(postService.commentDelete(commentId)){
			throw new PageRedirectException("삭제되었습니다");
		}	
		return "index";
	}
	
	/** 게시글 추천 */
	@RequestMapping(value="/mileage/recommendPosting", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> setRecommend(HttpSession session, 
			@RequestParam String userId, @RequestParam Integer postingId){
		
		String postingType = setRedirectInfoPage(session, postingId);		
		Map<String, Object> resultMap = new HashMap<>();
		
		boolean recommendResult = false;
		
		recommendResult = postService.setRecommend(postingId, postingType, userId);
		
		if(!recommendResult){
			resultMap.put("msg", "이미 추천하셨습니다");
		} 
		else {
			resultMap.put("msg", "추천되었습니다");
		}
		
		resultMap.put("count", postService.getRecommendsCount(postingId, postingType));
		
		return resultMap;
	}
	
	/** 게시판 글 작성 로직 */
	private String addPosting(Model model, HttpSession session,
			MultipartHttpServletRequest mrequest, MultipartFile uploadPhoto,
			String failMapping, boolean isNewPosting){
		
		String postingType = setRedirectPage(session);
		String userId = identify.getUserId(session);
		
		// multipart/form-data 타입 form 데이터 전달
		String postingTitle = mrequest.getParameter("postingTitle");
		String postingContent = mrequest.getParameter("postingContent");
		String postingId = mrequest.getParameter("postingId");
		String isDeletePhoto = mrequest.getParameter("deletePhoto");
		
		Posting postingData = new Posting(postingType, userId, postingTitle, postingContent, postService.DEFAULT_PHOTO_NAME);
		
		if(postingType == null || postingTitle == null || postingContent == null) {
			throw new WrongRequestException();	
		} else if(postingTitle.equals("")){
			session.setAttribute("postingData", postingData);
			session.setAttribute("gotoPage", failMapping);
			throw new PageRedirectException("제목을 입력해주세요.");
		} else if(postingContent.equals("")){
			session.setAttribute("postingData", postingData);
			session.setAttribute("gotoPage", failMapping);
			throw new PageRedirectException("내용을 입력해주세요.");
		}
		
		// 게시글 수정시
		if(!isNewPosting && postingId != null && isDeletePhoto != null){
			postingData.setPostingId(Integer.parseInt(postingId));
			// 업로드 되어있던 파일 삭제
			if(!isDeletePhoto.equals("false")){
				postingData.setPostingPhoto(isDeletePhoto);
				postService.uploadCancel(postingData, postService.DEFAULT_PHOTO_NAME);
			} else {
				postingData.setPostingPhoto(null);
			}
		} else if(!isNewPosting){
			throw new WrongRequestException();
		}

		if((isNewPosting && postService.postWrite(postingData)) ||
			(!isNewPosting && postService.postModify(postingData)))
			{
			// 이미지 업로드 처리
			if(!uploadPhoto.isEmpty()){
				int uploadResult = postService.upload(uploadPhoto, postingData);
				// 이미지 업로드 실패시 처리
				if(uploadResult == -1){
					throw new PageRedirectException("이미지 업로드에 실패하였습니다.");
				}
			}
			/* 정상 처리 */
			throw new PageRedirectException("등록되었습니다.");
		}
		
		return "index";
	}
	
	/** 전체 댓글 리스트를 하나의 리스트로 정렬하여 합침 */
	private List<PostingComment> mergeComment(List<PostingComment> parentDataList, List<PostingComment> childDataList){
		 
		List<PostingComment> mergedList = new ArrayList<>();
		int childIdx = 0;
		
		for(PostingComment parentData : parentDataList){
			int parentId = parentData.getCommentId();
			mergedList.add(parentData);
			
			for(; childIdx < childDataList.size();){
				PostingComment childData = childDataList.get(childIdx);
				Integer childId = childData.getCommentParentId();
				
				if(childId == null){
					childIdx++;
					continue;
				}
				// 현재 댓글이 부모 댓글일 때
				if(childId == parentId){
					mergedList.add(childData);
					childIdx++;
				}
				else{
					break;
				}	
			}
		}
		
		return mergedList;
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
	
	/**
	 * 게시판별로 복귀할 페이지 설정
	 * @param session
	 * @return 게시판 종류
	 */
	private String setRedirectPage(HttpSession session){
		session.removeAttribute("errorGotoPage");	
		String postingType = getPostingType(session);
		
		switch(postingType){
		case BOARD_TYPE_FOOD:
			session.setAttribute("errorGotoPage", "/foodMain");
			session.setAttribute("gotoPage", "/foodMain");
			break;
		case BOARD_TYPE_PLAY:
			session.setAttribute("errorGotoPage", "/playMain");
			session.setAttribute("gotoPage", "/playMain");
			break;
		case BOARD_TYPE_PLACE:
			session.setAttribute("errorGotoPage", "/placeMain");
			session.setAttribute("gotoPage", "/placeMain");
			break;
		default: // 학업 게시판, 미구현
			session.setAttribute("errorGotoPage", "/main");
			session.setAttribute("gotoPage", "/main");
			break;
		}
		
		return postingType;
	}
	
	/**
	 * 게시판별로 복귀할 페이지 설정(게시글 상세 보기 중)
	 * @param session
	 * @return 게시판 종류
	 */
	private String setRedirectInfoPage(HttpSession session, Integer postingId){
		session.removeAttribute("errorGotoPage");	
		String postingType = getPostingType(session);
		
		session.setAttribute("errorGotoPage", "/postingInfo?postingId=" + postingId);
		session.setAttribute("gotoPage", "/postingInfo?postingId=" + postingId);
	
		return postingType;
	}
}
