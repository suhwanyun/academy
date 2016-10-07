package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	
	/** 식사(먹거리)추천 게시판에 글 작성 */
	@RequestMapping(value="/add/food", method=RequestMethod.GET)
	public String addFood(){
		
		return "food/food_add";
	}
	
	/** 오락추천 게시판에 글 작성 */
	@RequestMapping(value="/add/play", method=RequestMethod.GET)
	public String addPlay(){
		
		return "play/play_add";
	}
	
	/** 명소추천 게시판에 글 작성 */
	@RequestMapping(value="/add/place", method=RequestMethod.GET)
	public String addPlace(){
		
		return "place/place_add";
	}
	

	/** 식사(먹거리)추천 게시판 글 내용 */
	@RequestMapping(value="/food_info", method=RequestMethod.GET)
	public String foodInfo(){
		
		return "food/food_info";
	}
	
	/** 오락추천 게시판 글 내용 */
	@RequestMapping(value="/play_info", method=RequestMethod.GET)
	public String playInfo(){
		
		return "play/play_info";
	}
	
	/** 오락추천 게시판 글 내용 */
	@RequestMapping(value="/place_info", method=RequestMethod.GET)
	public String placeInfo(){
		
		return "place/place_info";
	}
	
}
