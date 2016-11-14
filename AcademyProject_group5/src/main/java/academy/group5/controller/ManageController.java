package academy.group5.controller;

import java.text.ParseException;
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
import academy.group5.dto.Mileage;
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
	
	/** 로그 아웃 */
	@RequestMapping(value="/managerLogout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.removeAttribute("managerType");
		return "/login/login_manager";
	}
	
	/** 학기 추가
	 * @throws ParseException */
	@RequestMapping(value="/lectureManage/termSetting", method=RequestMethod.POST)
	public String termSetting(HttpSession session,
			@RequestParam String termStart, @RequestParam String termEnd){		
		// 에러 발생시 / 작업 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/termSettingjsp");
		session.setAttribute("gotoPage", "/lectureManage/main");
		
		service.registTerm(termStart, termEnd);
		
		throw new PageRedirectException("학기가 설정되었습니다");
	}
	
	/** 강의 등록 관리자 메인화면 강의 목록 검색 */
	@RequestMapping(value="/lectureManage/search", method=RequestMethod.GET)
	public String manageLectureMainSearch(HttpSession session, Model model,
			@RequestParam String searchType, @RequestParam String searchData){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/main");
		
		List<Lecture> lectureList = null;
		int pageCount = 1;
		// 검색 데이터가 없으면 기존 검색 데이터 삭제 후 전체 강의 목록 조회
		if(searchType.equals("") || searchData.equals("")){
			session.removeAttribute("searchType");
			session.removeAttribute("searchData");
			lectureList = service.getAllLectureList();
			pageCount = service.getMaxLectureListPage();
		} 
		// 검색 데이터를 저장 후 DB 조회
		else {
			session.setAttribute("searchType", searchType);
			session.setAttribute("searchData", searchData);
			lectureList = service.getAllLectureListBySearch(1, searchData, searchType);
			pageCount = service.getMaxLectureListPageBySearch(searchData, searchType);
		}
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("pageCount", pageCount);
		
		return "/manage/lecture/lecture";
	}
	
	/** 강의 등록 관리자 메인화면 페이징 */
	@RequestMapping(value="/lectureManage/page", method=RequestMethod.GET)
	public @ResponseBody List<Lecture> manageLectureMainPaging(HttpSession session, @RequestParam Integer page){
				
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/main");
		// 저장된 검색 데이터
		Object typeObj = session.getAttribute("searchType");
		Object dataObj = session.getAttribute("searchData");
		
		String searchType = typeObj == null ? null : (String)typeObj;
		String searchData = dataObj == null ? null : (String)dataObj;
				
		List<Lecture> lectureList = service.getAllLectureListBySearch(page, searchType, searchData);
		return lectureList;
	}
	
	/** 강의 등록 */
	@RequestMapping(value="/lectureManage/add", method=RequestMethod.POST)
	public String addLecture(HttpSession session, @RequestParam Integer lectureId, @RequestParam String lectureName,
			@RequestParam String professorName, @RequestParam Integer lectureClass){
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/lectureManage/addjsp");
		session.setAttribute("gotoPage", "/lectureManage/main");
		service.registLecture(lectureId, lectureName, professorName, lectureClass);	
		
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
		service.registLectureTime(timeData);

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
	
	// ---------------------------------마일리지 등록 관리자--------------------------------- */
	
	/** 강의 등록 관리자 메인화면 페이징 */
	@RequestMapping(value="/mileageManage/page", method=RequestMethod.GET)
	public @ResponseBody List<Mileage> manageMileageMainPaging(HttpSession session, @RequestParam Integer page){
				
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/mileageManage/main");
		// 저장된 정렬 데이터
		// 마일리지 순 정렬 : orderType = 'mileValue'
		// 내림차순 정렬 : isAsc = 'false'
		Object typeObj = session.getAttribute("orderType");
		Object ascObj = session.getAttribute("isAsc");
		
		String searchType = typeObj == null ? null : (String)typeObj;
		boolean isAsc = ascObj == null ? true : false;
				
		List<Mileage> mileageList = service.getAllMileage(page, searchType, isAsc);
		return mileageList;
	}
	
	/** 마일리지 등록 페이지 */
	@RequestMapping(value="/mileageManage/add", method=RequestMethod.GET)
	public String addMileage(){
		
		return "/manage/mileage/mileage_add";
	}
	
	/** 마일리지 관리 페이지 */
	@RequestMapping(value="/mileageManage/manage", method=RequestMethod.GET)
	public String manageMileage(){
		
		return "/manage/mileage/manage";
	}
}
