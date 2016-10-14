package academy.group5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import academy.group5.dto.Posting;
import academy.group5.service.PostingService;

@Controller
public class BoardController {
	
	@Autowired
	PostingService postService;
	
	/** 식사(먹거리)추천 게시판에 글 작성 */
	@RequestMapping(value="/write/food", method=RequestMethod.POST)
	public String addFood(Model model, RedirectAttributes redAttr, @RequestParam Posting data){
		
		if(postService.postWrite(data)){
			redAttr.addFlashAttribute("msg", "등록되었습니다.");
			return "redirect:/foodMain";
		} else {
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.\\n인터넷 연결을 확인하세요");
			model.addAttribute("postingData", data);
			return "/food/food_add";
		}
		
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
