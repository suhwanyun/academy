package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import academy.group5.dto.LectureTime;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
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
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/managerLoginjsp");
		
		String type = service.managerLogin(managerId, managerPass);	
		session.setAttribute("managerType", type);

		// 호출할 컨트롤러 지정
		if(type.equals(ManagerService.TYPE_LECTURE)){
			session.setAttribute("gotoPage", "/lectureManage/main");
		} else {
			session.setAttribute("gotoPage", "/mileageManage/main");
		}
		throw new PageRedirectException();
	}
	
	/** 강의 등록 */
	@RequestMapping(value="/lectureManage/add", method=RequestMethod.POST)
	public String addLecture(HttpSession session, @RequestParam Integer lectureId, @RequestParam String lectureName,
			@RequestParam String professorName, @RequestParam Integer lectureClass){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/addjsp");
		session.setAttribute("gotoPage", "/lectureManage/main");
		service.registerLecture(lectureId, lectureName, professorName, lectureClass);	
		
		throw new PageRedirectException("등록되었습니다.");
	}
	
	/** 강의 시간 등록 */
	@RequestMapping(value="/lectureManage/timeAdd", method=RequestMethod.POST)
	public String addLectureTime(HttpSession session, LectureTime timeData){
		
		Integer lectureId = timeData.getLectureId();
		Integer LectureClass = timeData.getLectureClass();
		// 에러 발생시 / 처리 완료시 이동할 페이지
		if(lectureId == null || LectureClass == null){
			session.setAttribute("errorGotoPage", "/lectureManage/main");
			throw new WrongRequestException();
		}
		session.setAttribute("errorGotoPage", "/lectureManage/timeAddjsp?lectureId="+lectureId+"&lectureClass="+LectureClass);
		session.setAttribute("gotoPage", "/lectureManage/main");
		service.registerLectureTime(timeData);

		throw new PageRedirectException("등록되었습니다.");
	}
	
	/** 강의 관리 */
	@RequestMapping(value="/lectureManage/manage", method=RequestMethod.POST)
	public String manageLecture(HttpSession session,
			@RequestParam int lectureId, @RequestParam int lectureClass,
			@RequestParam String lectureName, @RequestParam String professorName){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/managejsp?lectureId="+lectureId+"&lectureClass="+lectureClass);
		session.setAttribute("gotoPage", "/lectureManage/main");	
		service.updateLecture(lectureId, lectureClass, lectureName, professorName);
		
		throw new PageRedirectException("수정되었습니다.");
	}
	
	/** 강의 시간 관리 */
	@RequestMapping(value="/lectureManage/timeManage", method=RequestMethod.POST)
	public String manageLectureTime(HttpSession session, LectureTime timeData){
		
		Integer lectureTimeId = timeData.getLectureTimeId();
		// 에러 발생시 / 처리 완료시 이동할 페이지
		if(lectureTimeId == null){
			session.setAttribute("errorGotoPage", "/lectureManage/main");
			throw new WrongRequestException();
		}else {
			session.setAttribute("errorGotoPage", "/lectureManage/timeManagejsp?lectureTimeId="+lectureTimeId);
		}
		session.setAttribute("gotoPage", "/lectureManage/main");	
		service.updateLectureTime(timeData);
		
		throw new PageRedirectException("수정되었습니다.");
	}
	
	/** 강의 삭제 */
	@RequestMapping(value="/lectureManage/drop", method=RequestMethod.POST)
	public String dropLecture(HttpSession session, Model model,
			@RequestParam int lectureId, @RequestParam int lectureClass){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/main");
		session.setAttribute("gotoPage", "/lectureManage/main");	
		service.deleteLecture(lectureId, lectureClass);
		
		throw new PageRedirectException("삭제되었습니다.");
	}
	
	/** 강의 시간 삭제 */
	@RequestMapping(value="/lectureManage/timeDrop", method=RequestMethod.POST)
	public String dropLectureTime(HttpSession session, @RequestParam int lectureTimeId){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/main");
		session.setAttribute("gotoPage", "/lectureManage/main");	
		service.deleteLectureTime(lectureTimeId);
		
		throw new PageRedirectException("삭제되었습니다.");
	}
	
	/** 마일리지 등록 관리자 메인 페이지 */
	@RequestMapping(value="/mileageManage/main", method=RequestMethod.GET)
	public String manageMileageMainPage(){
	
		return "/manage/mileage";
	}
	
	/** 마일리지 등록 페이지 */
	@RequestMapping(value="/mileageManage/add", method=RequestMethod.GET)
	public String addMileage(){
		
		return "/manage/mileage_add";
	}
	
	/** 마일리지 관리 페이지 */
	@RequestMapping(value="/mileageManage/manage", method=RequestMethod.GET)
	public String manageMileage(){
		
		return "/manage/mileage_manage";
	}
}
