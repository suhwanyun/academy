package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManageController {
	
	/** 강의등록 관리자 메인 페이지 */
	@RequestMapping(value="/manage/lectureMain", method=RequestMethod.GET)
	public String manageLectureMainPage(){
	
		return "/manage/lecture";
	}
	
	/** 강의 등록 페이지 */
	@RequestMapping(value="/manage/lectureAdd", method=RequestMethod.GET)
	public String addLecture(){
		
		return "manage/lecture_add";
	}
	
	/** 강의 관리 페이지 */
	@RequestMapping(value="/manage/lectureManage", method=RequestMethod.GET)
	public String manageLecture(){
		
		return "manage/lecture_manage";
	}
	
	/** 강의 시간 등록 페이지 */
	@RequestMapping(value="/manage/lectureTimeAdd", method=RequestMethod.GET)
	public String addLectureTime(){
		
		return "manage/lecture_time_add";
	}
	
	/** 강의 시간 관리 페이지 */
	@RequestMapping(value="/manage/lectureTimeManage", method=RequestMethod.GET)
	public String manageLectureTime(){
		
		return "manage/lecture_time_manage";
	}
	
	/** 마일리지 등록 관리자 메인 페이지 */
	@RequestMapping(value="/manage/mileageMain", method=RequestMethod.GET)
	public String manageMileageMainPage(){
	
		return "/manage/mileage";
	}
	
	/** 마일리지 등록 페이지 */
	@RequestMapping(value="/manage/mileageAdd", method=RequestMethod.GET)
	public String addMileage(){
		
		return "manage/mileage_add";
	}
	
	/** 마일리지 관리 페이지 */
	@RequestMapping(value="/manage/mileageManage", method=RequestMethod.GET)
	public String manageMileage(){
		
		return "manage/mileage_manage";
	}
}
