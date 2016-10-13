package academy.group5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import academy.group5.service.LectureService;

@Controller
public class CampusController {
	
	@Autowired
	LectureService lecService;
	
	/** 기존 알림 목록 표시 */
	@RequestMapping(value="/campus/notiList", method=RequestMethod.GET)
	public String userNotiList(){
		
		return "campus/noti_list";
	}
	
	/** 선택한 알림의 자세한 내용 표시 */
	@RequestMapping(value="/campus/notiInfo", method=RequestMethod.GET)
	public String userNotiInfo(){
		
		return "campus/noti_info";
	}
	
	/** 전체 강의 목록 표시 */
	@RequestMapping(value="/campus/lectureList", method=RequestMethod.GET)
	public String getlectureList(Model model){
		
		return "campus/lecture_list";
	}
	
	/** 선택한 강의들의 시간표 */
	@RequestMapping(value="/campus/schedule", method=RequestMethod.GET)
	public String schedule(){
		
		return "campus/schedule";
	}
}
