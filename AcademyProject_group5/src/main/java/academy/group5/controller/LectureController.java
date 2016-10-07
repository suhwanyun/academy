package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LectureController {
	
	/** 학생이 선택한 강의 목록 표시 */
	@RequestMapping(value="/selectedLectureList", method=RequestMethod.GET)
	public String selectedLectureList(){
		
		return "campus/lecture/lecture_list";
	}
	
	/** 학생이 선택한 강의의 메인 페이지 */
	@RequestMapping(value="/lectureMain", method=RequestMethod.GET)
	public String lectureMainPage(){
		
		return "campus/lecture/lecture_main";
	}
	
	/** 학생이 선택한 강의의 게시판 페이지 */
	@RequestMapping(value="/lectureBoard", method=RequestMethod.GET)
	public String lectureBoard(){
		
		return "campus/lecture/lecture_board";
	}
	
	/** 학생이 선택한 강의의 게시판 글 내용 확인 */
	@RequestMapping(value="/lectureBoardInfo", method=RequestMethod.GET)
	public String lectureBoardInfo(){
		
		return "campus/lecture/lecture_board_info";
	}
	
	/** 학생이 선택한 강의의 게시판에 글 작성 */
	@RequestMapping(value="/lectureBoardAdd", method=RequestMethod.GET)
	public String lectureBoardAdd(){
		
		return "campus/lecture/lecture_board_add";
	}
	
	
	/** 학생이 선택한 강의의 공지사항 목록 */
	@RequestMapping(value="/lectureNotiList", method=RequestMethod.GET)
	public String lectureNotiList(){
		
		return "campus/lecture/lecture_noti_list";
	}
	
	/** 학생이 선택한 강의의 공지사항 중 선택한 공지의 정보 */
	@RequestMapping(value="/lectureNotiInfo", method=RequestMethod.GET)
	public String lectureNotiInfo(){
		
		return "campus/lecture/lecture_noti_info";
	}
	
	/** 학생이 선택한 강의의 알림 등록 */
	@RequestMapping(value="/lectureNotiAdd", method=RequestMethod.GET)
	public String lectureNotiAdd(){
		
		return "campus/lecture/lecture_noti_add";
	}
	
	
	/** 학생이 선택한 강의의 정보를 확인하거나 강의 취소 */
	@RequestMapping(value="/lectureInfo", method=RequestMethod.GET)
	public String lectureInfo(){
		
		return "campus/lecture/lecture_info";
	}
}
