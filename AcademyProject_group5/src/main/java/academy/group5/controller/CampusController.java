package academy.group5.controller;

import java.util.ArrayList;
import java.util.Date;
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
import academy.group5.dto.etc.UserLectureNotice;
import academy.group5.dto.etc.UserLectureTime;
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
	public String addUserNotiList(HttpSession session, Model model){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		
		String userId = identify.getUserId(session);
		// 기존 페이지 더보기 여부 확인
		Object pageObj = session.getAttribute("page");
		Integer prevPageRecord = pageObj == null ? 1 : (Integer)pageObj;
		
		// 기존에 더보기 했던 페이지 복구
		List<UserLectureNotice> noticeList = new ArrayList<>();
		for(int pageIdx = 1; pageIdx <= prevPageRecord; pageIdx++){
			noticeList.addAll(lecNotiService.allLectureNoticeList(userId, pageIdx));
		}
		model.addAttribute("noticeList", noticeList);
		
		return "/campus/noti_list";
	}
	
	/** 기존 알림 목록 표시(더보기) */
	@RequestMapping(value="/campus/notiListMore", method=RequestMethod.GET)
	public @ResponseBody List<UserLectureNotice> getUserNotiList(HttpSession session, Model model){
		
		String userId = identify.getUserId(session);
		Object pageObj = session.getAttribute("page");
		Integer page = 2;
		if(pageObj != null){
			page = (Integer)pageObj + 1;
		}
		session.setAttribute("page", page);
		
		List<UserLectureNotice> noticeList = lecNotiService.allLectureNoticeList(userId, page);
		model.addAttribute("noticeList", noticeList);
		
		return noticeList;
	}
	
	/** 선택한 알림의 자세한 내용 표시 */
	@RequestMapping(value="/campus/notiInfo", method=RequestMethod.GET)
	public String userNotiInfo(HttpSession session, Model model, @RequestParam Integer lectureNoticeId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/notiList");
		// 현재 열린 탭 저장
		session.setAttribute("nowTab", "notification");
				
		LectureNotice noticeData = lecNotiService.lectureNoticeInfo(lectureNoticeId);
		model.addAttribute("lectureNotice", noticeData);
		
		return "/campus/noti_info";
	}
	
	/** 전체 강의 목록 표시 */
	@RequestMapping(value="/campus/lectureList", method=RequestMethod.GET)
	public String allLectureList(Model model, HttpSession session){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
	
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
		List<UserLectureTime> lectureData = lecService.userLectureList(userId);
		
		model.addAttribute("lectureList", lectureData);
		return "/campus/lecture/lecture_list";
	}
	
	/** 선택한 강의들의 시간표 출력 페이지 */
	@RequestMapping(value="/campus/schedulejsp", method=RequestMethod.GET)
	public String schedulePage(HttpSession session, Model model){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		
		return "/campus/schedule";
	}
	
	/** 선택한 강의들의 시간표 데이터 */
	@RequestMapping(value="/campus/schedule", method=RequestMethod.GET)
	public @ResponseBody List<UserLectureTime> scheduleData(HttpSession session, Model model){
				
		String userId = identify.getUserId(session);
		List<UserLectureTime> timetableData = lecService.timetable(userId);
		
		return timetableData;
	}
}
