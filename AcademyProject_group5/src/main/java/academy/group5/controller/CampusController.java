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
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.service.LectureNoticeService;
import academy.group5.service.LectureService;
import academy.group5.util.Identify;

/**
 * 학업 컨트롤러
 * @author YSH
 *
 */
@Controller
public class CampusController {
	
	@Autowired
	LectureService lecService;
	
	@Autowired
	LectureNoticeService lecNotiService;
	
	Identify identify = new Identify();
	
	/** 기존 알림 목록 표시 */
	@RequestMapping(value="/campus/notiList", method=RequestMethod.GET)
	public String userNotiList(HttpSession session, Model model){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		
		String userId = identify.getUserId(session);
		List<LectureNotice> noticeList = lecNotiService.allLectureNoticeList(userId, 1);
		model.addAttribute("noticeList", noticeList);
		
		return "/campus/noti_list";
	}
	
	/** 선택한 알림의 자세한 내용 표시 */
	@RequestMapping(value="/campus/notiInfo", method=RequestMethod.GET)
	public String userNotiInfo(){
		
		return "/campus/noti_info";
	}
	
	/** 전체 강의 목록 표시 */
	@RequestMapping(value="/campus/lectureList", method=RequestMethod.GET)
	public String allLectureList(Model model, HttpSession session){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		
		/*// 현재 열림 탭 저장
		model.addAttribute("nowTab", "lectureList");*/
	
		session.removeAttribute("searchType");
		session.removeAttribute("searchData");
		
		List<Lecture> lecList = lecService.allLectureList(1, null, null);
		if(lecList.size() != 0){
			model.addAttribute("lectureList", lecList);
		}
		return "/campus/lecture_list";
	}
	
	/** 전체 강의 목록 표시(더보기) */
	@RequestMapping(value="/campus/lectureListMore", method=RequestMethod.GET)
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
	
	/** 전체 강의 목록 중 검색 */
	@RequestMapping(value="/campus/lectureListSearch", method=RequestMethod.GET)
	public @ResponseBody List<Lecture> setSearchDataForGetlectureList(HttpSession session,
			@RequestParam String searchType, @RequestParam String searchData){
		
		if(searchType.equals("") || searchData.equals("")){
			session.removeAttribute("searchType");
			session.removeAttribute("searchData");
			
			return lecService.allLectureList(1, null, null);
		} else {
			session.setAttribute("searchType", searchType);
			session.setAttribute("searchData", searchData);
			
			return lecService.allLectureList(1, searchData, searchType);
		}
	}
	
	/** 학생이 선택한 강의 목록 표시 */
	@RequestMapping(value="/campus/selectedLectureList", method=RequestMethod.GET)
	public String selectedLectureList(HttpSession session, Model model){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		
		String userId = identify.getUserId(session);
		List<Lecture> lectureData = lecService.userLectureList(userId);
		model.addAttribute("lectureList", lectureData);
		
		return "/campus/lecture/lecture_list";
	}
	
	/** 선택한 강의들의 시간표 */
	@RequestMapping(value="/campus/schedule", method=RequestMethod.GET)
	public String schedule(HttpSession session, Model model){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
				
		String userId = identify.getUserId(session);
		List<LectureTime> timetableData = lecService.timetable(userId);
		model.addAttribute("timetable", timetableData);
		
		return "/campus/schedule";
	}
}
