package academy.group5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		Object typeObj = session.getAttribute("searchType");
		String searchData = dataObj == null ? null : (String)dataObj;
		String searchType = dataObj == null ? null : (String)typeObj;
		
		List<Lecture> lecList = lecService.allLectureList(page == null ? 
									1 : Integer.parseInt(page), searchData, searchType);
		return lecList;
	}
	
	/** 전체 강의 목록 중 강의명 검색 */
	@RequestMapping(value="/campus/lectureListSearch", method=RequestMethod.GET)
	public String setSearchDataForGetlectureList(Model model, HttpSession session,
			@RequestParam String searchType, @RequestParam String searchData){
		
		if(searchType.equals("") || searchData.equals("")){
			session.removeAttribute("searchType");
			session.removeAttribute("searchData");
			
			searchType = null;
			searchData = null;
		} else {
			session.setAttribute("searchType", searchType);
			session.setAttribute("searchData", searchData);
		}
		
		List<Lecture> lecList = lecService.allLectureList(1, searchData, searchType);
		if(lecList.size() != 0){
			model.addAttribute("lectureList", lecList);
		}

		return "/campus/lecture_list";
	}
	
	/** 선택한 강의들의 시간표 */
	@RequestMapping(value="/campus/schedule", method=RequestMethod.GET)
	public String schedule(){
		
		return "campus/schedule";
	}
}
