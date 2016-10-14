package academy.group5.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.service.PostingService;

@Controller
public class BoardController {
	
	@Autowired
	PostingService postService;
	
	/** 식사(먹거리)추천 게시판에 글 작성 
	 * @throws IOException 
	 * @throws IllegalStateException */
	@RequestMapping(value="/write/food", method=RequestMethod.POST)
	public String addFood(Model model, HttpSession session, RedirectAttributes redAttr,
			MultipartHttpServletRequest mrequest,
			@RequestParam(required=false) MultipartFile uploadPhoto){
		
		// 로그인된 id 확인
		String userId = ((UserData)session.getAttribute("user")).getUserId();
		
		// multipart/form-data 타입 form 데이터 전달
		String postingType = mrequest.getParameter("postingType");
		String postingTitle = mrequest.getParameter("postingTitle");
		String postingContent = mrequest.getParameter("postingContent");
		
		Posting postingData = new Posting(postingType, userId, postingTitle, postingContent);
		
		if(userId == null || postingType == null || postingTitle == null || postingContent == null){
			model.addAttribute("msg", "입력된 정보가 올바르지 않습니다.");
			model.addAttribute("posting", postingData);
			return "/food/food_add";
		}
		
		if(postService.postWrite(postingData)){
			if(uploadPhoto != null){
				// 원본 파일명
				String originalName = uploadPhoto.getOriginalFilename();
				// 파일 확장자 추출
				String fileTypeStr = originalName.substring(originalName.lastIndexOf("."), originalName.length());
				// 게시글 번호
				Integer postingId = postService.getPostingId(postingData);
				
				if(postingId == 0){
					return uploadError(redAttr);
				}
				// 파일명 : 게시판종류 + 게시글번호 + 확장자
				String fileName = postingType + "_" + postingId + fileTypeStr;			
				File file = new File(fileName);
				
				// 파일 업로드
				try {
					uploadPhoto.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					return uploadError(redAttr);
				}
				
				// DB에 저장된 파일의 이름을 등록
				postingData.setPostingPhoto(fileName);
				if(!postService.photoRegister(postingData)){
					return uploadError(redAttr);
				}
			}
			
			redAttr.addFlashAttribute("msg", "등록되었습니다.");
			return "redirect:/foodMain";
		} else {
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.\\n인터넷 연결을 확인하세요");
			model.addAttribute("posting", postingData);
			return "/food/food_add";
		}
	}
	
	// 이미지 업로드 실패시 처리
	private String uploadError(RedirectAttributes redAttr){
		redAttr.addFlashAttribute("msg", "이미지 업로드에 실패하였습니다.");
		return "redirect:/foodMain";
	}
	
	/** 오락추천 게시판에 글 작성 */
	@RequestMapping(value="/write/play", method=RequestMethod.POST)
	public String addPlay(Model model, @RequestParam Posting data){
		return "/play/play_add";
	}
	
	/** 명소추천 게시판에 글 작성 */
	@RequestMapping(value="/write/place", method=RequestMethod.POST)
	public String addPlace(Model model, @RequestParam Posting data){
		return "/place/place_add";
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
