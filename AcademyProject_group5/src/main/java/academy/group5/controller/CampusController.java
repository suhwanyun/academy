package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CampusController {
	
	// 기존 알림 목록 표시
	@RequestMapping(value="/notiList", method=RequestMethod.GET)
	public String userNotiList(Model model){
		
		return "campus/noti_list";
	}
	
	// 선택한 알림의 자세한 내용 표시
	@RequestMapping(value="/notiInfo", method=RequestMethod.GET)
	public String userNotiInfo(Model model){
		
		return "campus/noti_info";
	}
	
	// 전체 강의 목록 표시 페이지
	@RequestMapping(value="/lectureList", method=RequestMethod.GET)
	public String allLectureList(Model model){
		
		return "campus/lecture_list";
	}
	
	// 선택한 강의들의 시간표
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String schedule(Model model){
		
		return "campus/schedule";
	}
}