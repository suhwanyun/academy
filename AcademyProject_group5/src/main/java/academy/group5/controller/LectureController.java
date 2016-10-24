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
	
	/** 학생이 선택한 강의 목록 표시 */
	@RequestMapping(value="/lecture/selectedLectureList", method=RequestMethod.GET)
	public String selectedLectureList(){
		
		return "/campus/lecture/lecture_list";
	}
	
	/** 학생이 선택한 강의의 메인 페이지 */
	@RequestMapping(value="/lecture/lectureMain", method=RequestMethod.GET)
	public String lectureMainPage(){
		
		return "/campus/lecture/lecture_main";
	}
	
	/** 학생이 선택한 강의의 게시판 페이지 */
	@RequestMapping(value="/lecture/lectureBoard", method=RequestMethod.GET)
	public String lectureBoard(){
		
		return "/campus/lecture/lecture_board";
	}
	
	/** 학생이 선택한 강의의 게시판 글 내용 확인 *//*
	@RequestMapping(value="/lecture/lectureBoardInfo", method=RequestMethod.GET)
	public String lectureBoardInfo(){
		
		return "/campus/lecture/lecture_board_info";
	}*/
	
	/** 학생이 선택한 강의의 게시판에 글 작성 */
	@RequestMapping(value="/lecture/lectureBoardAdd", method=RequestMethod.GET)
	public String lectureBoardAdd(){
		
		return "/campus/lecture/lecture_board_add";
	}
	
	
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
	public String lectureInfo(Model model,
			@RequestParam Integer lectureId, @RequestParam Integer lectureClass){
		
		Lecture selectedlecture = lecService.lectureClassInfo(lectureId, lectureClass);
		List<LectureTime> selectedLectureTimes = lecService.lectureTimeInfo(selectedlecture);
		
		model.addAttribute("lectureData", selectedlecture);
		model.addAttribute("lectureTime", selectedLectureTimes);
		
		return "/campus/lecture/lecture_info";
	}
	
	/** 강의 신청 */
	@RequestMapping(value="/lecture/lectureApply", method=RequestMethod.GET)
	public @ResponseBody String lectureApplying(HttpSession session,
			@RequestParam Integer lectureId, @RequestParam Integer lectureClass){
		
		String userId = identify.getUserId(session);
		lecService.apply(lectureId, lectureClass, userId);
		
		return "신청이 정상적으로 되었습니다.";
	}
}
