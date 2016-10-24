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
	public String postingInfo(Model model, HttpSession session, 
			@RequestParam int postingId, @RequestParam(required=false) String sendmsg){
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
			logger.trace("parent:{}", parentData);
			for(; childIdx < childDataList.size();){
				PostingComment childData = childDataList.get(childIdx);
				Integer childId = childData.getCommentParentId();
				logger.trace("child:{}", childData);
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
			}
		}
		model.addAttribute("commentList", commentList);
		
		// 다른 페이지에서 전달 받은 메세지
		if(sendmsg != null){
			model.addAttribute("msg", sendmsg);
		}
		
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
	public String postingUpdate(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest, @RequestParam(required=false) MultipartFile uploadPhoto){
		String postingType = getPostingType(session);
		
		String okMappingStr;
		String failMappingStr;
		
		switch(postingType){
		case BOARD_TYPE_FOOD:
			okMappingStr = "redirect:/foodMain";
			failMappingStr = "/write/foodUpdatejsp";
			break;
		case BOARD_TYPE_PLAY:
			okMappingStr = "redirect:/playMain";
			failMappingStr = "/write/playUpdatejsp";
			break;
		case BOARD_TYPE_PLACE:
			okMappingStr = "redirect:/placeMain";
			failMappingStr = "/write/placeUpdatejsp";
			break;
		default: // 학업 게시판, 미구현
			okMappingStr = "/index";
			failMappingStr = "/index";
			break;
		}
		
		return addPosting(model, session, redAttr, mrequest, uploadPhoto,
				okMappingStr, failMappingStr, false);	
	}
	
	/** 게시글 삭제 */
	@RequestMapping(value="/write/postingDelete", method=RequestMethod.GET)
	public String deletePosting(HttpSession session, RedirectAttributes redAttr,
			@RequestParam Integer postingId){
		String userId = identify.getUserId(session);	
		String postingType = getPostingType(session);
	
		// 게시글 삭제
		if(postService.postDelete(userId, postingId, postingType)){
			redAttr.addFlashAttribute("msg", "삭제되었습니다.");
		} 
		
		switch(postingType){
		case BOARD_TYPE_FOOD:
			return "redirect:/foodMain";
		case BOARD_TYPE_PLAY:
			return "redirect:/playMain";
		case BOARD_TYPE_PLACE:
			return "redirect:/placeMain";
		default: // 학업 게시판, 미구현
			return "redirect:/main";
		}
		
	}
	
	/** 댓글 추가 */
	@RequestMapping(value="/write/addComment", method=RequestMethod.POST)
	public @ResponseBody Map<String, List<PostingComment>> getCommentList(Model model, HttpSession session,
				@RequestParam Integer postingId, @RequestParam(required=false) Integer commentParentId, @RequestParam String commentContent){
		String userId = identify.getUserId(session);		
		String postingType = getPostingType(session);
		
		PostingComment commentData = new PostingComment(null, postingId, postingType, userId, 
														commentParentId, null, commentContent);
		try{
			postService.commentWrite(commentData);
	
		} catch(PersistenceException e){
			model.addAttribute("error", "이미 삭제된 댓글입니다.");
		}
		
		Map<String, List<PostingComment>> commentList = postService.commentList(postingId, postingType);
		
		return commentList;
	}
	
	/** 댓글 수정 */
	@RequestMapping(value="/write/updateComment", method=RequestMethod.POST)
	public @ResponseBody Map<String, List<PostingComment>> updateComment(Model model, HttpSession session,
				@RequestParam Integer postingId, @RequestParam String commentContent){
		String userId = identify.getUserId(session);	
		String postingType = getPostingType(session);
		
		PostingComment commentData = new PostingComment(null, postingId, null, userId, 
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
	public String deleteComment(RedirectAttributes redAttr,
			@RequestParam Integer postingId, @RequestParam Integer commentId){
		
		if(postService.commentDelete(commentId)){
			redAttr.addFlashAttribute("sendmsg", "삭제되었습니다.");
		}	
		return "redirect:/postingInfo?postingId=" + postingId;
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
		String postingId = mrequest.getParameter("postingId");
		String isDeletePhoto = mrequest.getParameter("deletePhoto");
		
		if(isError || postingType == null || postingTitle == null || postingContent == null) {
			throw new WrongRequestException();	
		} else if(postingTitle.equals("")){
			model.addAttribute("msg", "제목을 입력해주세요.");
			isError = true;
		} else if(postingContent.equals("")){
			model.addAttribute("msg", "내용을 입력해주세요.");
			isError = true;
		}
		
		Posting postingData = new Posting(postingType, userId, postingTitle, postingContent, postService.DEFAULT_PHOTO_NAME);
		
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
		}

		// 에러가 발생하여 작성화면으로 돌아가기
		model.addAttribute("postingData", postingData);
		return failMapping;
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
}
