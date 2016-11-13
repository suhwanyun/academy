package academy.group5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.LectureNoticeSetTime;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.LectureNoticeService;
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
	
	@Autowired
	LectureNoticeService lecNotiService;
	
	Identify identify = new Identify();
	
	/** 학생이 선택한 강의의 알림 등록 */
	@RequestMapping(value="/lecture/lectureNotiAdd", method=RequestMethod.POST)
	public String lectureNotiAdd(HttpSession session, @RequestParam String noticeType,
			@RequestParam String noticeTitle, @RequestParam String noticeContent){
		
		Object idObj = session.getAttribute("lectureId");
		Object classObj = session.getAttribute("lectureClass");
		
		// 에러 발생시 / 처리 완료시 이동할 페이지
		if(idObj != null && classObj != null){
			session.setAttribute("errorGotoPage", "/lecture/lectureMain?lectureId="+idObj+"&lectureClass="+classObj);
			session.setAttribute("gotoPage", "/lecture/lectureMain?lectureId="+idObj+"&lectureClass="+classObj);
		} else {
			session.setAttribute("errorGotoPage", "/campus/campusMain");
			throw new WrongRequestException();
		}
		
		String userId = identify.getUserId(session);
		
		if(!lecService.getIsPresident((Integer)idObj, userId, (Integer)classObj)){
			throw new WrongRequestException("반장만 등록할 수 있습니다.");
		}
		// 알림 등록
		lecNotiService.postNotice(new LectureNotice((Integer)idObj, (Integer)classObj,
				noticeType, noticeTitle, noticeContent), true);
		
		throw new PageRedirectException("등록되었습니다.");
	}
	
	/** 강의의 시간 변경(일시적인) */
	@RequestMapping(value="/lecture/lectureTimeNotiAdd", method=RequestMethod.POST)
	public String lectureTimeNotiAdd(HttpSession session,
			@RequestParam LectureNoticeSetTime lectureTimeSetting){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/campus/campusMain");
		
		String userId = identify.getUserId(session);
		
		if(!lecService.getIsPresident(lectureTimeSetting.getLectureId(),
				userId, lectureTimeSetting.getLectureClass())){
			throw new WrongRequestException("반장만 등록할 수 있습니다.");
		}
		// 알림 등록
		lecNotiService.postNotice(lectureTimeSetting);
		
		throw new PageRedirectException("등록되었습니다.");
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
