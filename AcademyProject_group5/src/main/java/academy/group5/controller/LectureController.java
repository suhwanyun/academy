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
import academy.group5.dto.LectureTime;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.LectureService;
import academy.group5.util.Identify;

/**
 * 강의 컨트롤러
 * @author YSH
 *
 */
@Controller
public class LectureController {
	
	@Autowired
	LectureService lecService;
	
	Identify identify = new Identify();
	
	/** 학생이 선택한 강의의 공지사항 목록 */
	@RequestMapping(value="/lecture/lectureNotiList", method=RequestMethod.GET)
	public String lectureNotiList(){
		
		return "/campus/lecture/lecture_noti_list";
	}
	
	/** 학생이 선택한 강의의 공지사항 중 선택한 공지의 정보 */
	@RequestMapping(value="/lecture/lectureNotiInfo", method=RequestMethod.GET)
	public String lectureNotiInfo(){
		
		return "/campus/lecture/lecture_noti_info";
	}
	
	/** 학생이 선택한 강의의 알림 등록 */
	@RequestMapping(value="/lecture/lectureNotiAdd", method=RequestMethod.GET)
	public String lectureNotiAdd(){
		
		return "/campus/lecture/lecture_noti_add";
	}
	  
	/** 학생이 선택한 강의의 정보를 확인하거나 신청 취소 */
	@RequestMapping(value="/lecture/lectureInfo", method=RequestMethod.GET)
	public String lectureInfo(Model model, HttpSession session,
			@RequestParam Integer lectureId, @RequestParam Integer lectureClass){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		Lecture selectedlecture = lecService.lectureClassInfo(lectureId, lectureClass);
		List<LectureTime> selectedLectureTimes = lecService.lectureTimeInfo(selectedlecture);
		
		model.addAttribute("lectureData", selectedlecture);
		model.addAttribute("lectureTime", selectedLectureTimes);
		
		// 현재 열린 탭 저장
		session.setAttribute("nowTab", "lectureList");
		
		return "/campus/lecture/lecture_info";
	}
	
	/** 강의 신청 */
	@RequestMapping(value="/lecture/lectureApply", method=RequestMethod.POST)
	public String applyNewLecture(HttpSession session, @RequestParam Integer lectureId,
			@RequestParam Integer lectureClass, @RequestParam String isPresident){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lecture/lectureInfo?lectureId="+lectureId+"&lectureClass="+lectureClass);
		session.setAttribute("gotoPage", "/campus/campusMain");
				
		String userId = identify.getUserId(session);
		lecService.apply(lectureId, userId, lectureClass, isPresident);
				
		throw new PageRedirectException("신청되었습니다.");
	}
}
