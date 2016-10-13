package academy.group5.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import academy.group5.dto.Lecture;
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
	public @ResponseBody List<Lecture> getlectureList(HttpSession session,
				@RequestParam(required=false) String page){
		
		Object dataObj = session.getAttribute("searchData");	
		String searchData = dataObj == null ? null : (String)dataObj;
		
		List<Lecture> lecList = lecService.allLectureList(page == null ? 
									1 : Integer.parseInt(page), searchData);
		return lecList;
	}
	
	/** 전체 강의 목록 중 강의명 검색 */
	@RequestMapping(value="/campus/lectureListSearch", method=RequestMethod.GET)
	public String setSearchDataForGetlectureList(HttpSession session, @RequestParam String searchData){
		
		if(searchData == null || searchData.equals("")){
			session.removeAttribute("searchData");
		} else {
			session.setAttribute("searchData", searchData);
		}

		return "redirect:/campus/lectureList";
	}
	
	/** 선택한 강의들의 시간표 */
	@RequestMapping(value="/campus/schedule", method=RequestMethod.GET)
	public String schedule(){
		
		return "campus/schedule";
	}
}
