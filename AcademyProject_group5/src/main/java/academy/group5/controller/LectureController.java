package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LectureController {
	
	// 학생이 선택한 강의 목록 표시
	@RequestMapping(value="/selectedLectureList", method=RequestMethod.GET)
	public String selectedLectureList(Model model){
		
		return "campus/lecture/lecture_list";
	}
	
}
