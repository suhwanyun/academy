package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import academy.group5.service.ManagerService;

/**
 * 관리자 컨트롤러
 * @author YSH
 *
 */
@Controller
public class ManageController {
	
	@Autowired
	ManagerService service;
	
	/** 관리자 로그인 */
	@RequestMapping(value="/managerLogin", method=RequestMethod.POST)
	public String login(Model model, HttpSession session,
			@RequestParam String managerId, @RequestParam String managerPass){
		
		String type = service.managerLogin(managerId, managerPass);	
		session.setAttribute("managerType", type);
		
		if(type.equals(ManagerService.TYPE_LECTURE)){
			return "redirect:/lectureManage/main";
		} else {
			return "redirect:/mileageManage/main";
		}
	}
	
	/** 강의등록 관리자 메인 페이지 */
	@RequestMapping(value="/lectureManage/main", method=RequestMethod.GET)
	public String manageLectureMainPage(){
	
		return "/manage/lecture";
	}
	
	/** 강의 등록 페이지 */
	@RequestMapping(value="/lectureManage/add", method=RequestMethod.GET)
	public String addLecture(){
		
		return "manage/lecture_add";
	}
	
	/** 강의 관리 페이지 */
	@RequestMapping(value="/lectureManage/manage", method=RequestMethod.GET)
	public String manageLecture(){
		
		return "manage/lecture_manage";
	}
	
	/** 강의 시간 등록 페이지 */
	@RequestMapping(value="/lectureManage/timeAdd", method=RequestMethod.GET)
	public String addLectureTime(){
		
		return "manage/lecture_time_add";
	}
	
	/** 강의 시간 관리 페이지 */
	@RequestMapping(value="/lectureManage/timeManage", method=RequestMethod.GET)
	public String manageLectureTime(){
		
		return "manage/lecture_time_manage";
	}
	
	/** 마일리지 등록 관리자 메인 페이지 */
	@RequestMapping(value="/mileageManage/main", method=RequestMethod.GET)
	public String manageMileageMainPage(){
	
		return "/manage/mileage";
	}
	
	/** 마일리지 등록 페이지 */
	@RequestMapping(value="/mileageManage/add", method=RequestMethod.GET)
	public String addMileage(){
		
		return "manage/mileage_add";
	}
	
	/** 마일리지 관리 페이지 */
	@RequestMapping(value="/mileageManage/manage", method=RequestMethod.GET)
	public String manageMileage(){
		
		return "manage/mileage_manage";
	}
}
