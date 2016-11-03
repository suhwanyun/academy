package academy.group5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.exception.PageRedirectException;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.service.LoginService;
import academy.group5.service.PhoneService;
import academy.group5.util.Identify;

/**
 * 어플 컨트롤러
 * @author YSH
 *
 */
@Controller
public class PhoneController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	PhoneService phoneService;
	
	@Autowired
	GCMRepo gcmRepo;
	
	/** 어플 로그인 */
	@RequestMapping(value="/appLogin", method=RequestMethod.POST)
	public String login(Model model, HttpSession session,
			@RequestParam String userId, @RequestParam String userPass){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/loginjsp");
		
		UserData data = loginService.login(userId, userPass);		
		session.setAttribute("user", data);	

		return "phone_index";
	}
	
	/** GCM 등록 */
	@RequestMapping(value="/registGCM", method=RequestMethod.POST)
	public @ResponseBody String addFood(@RequestParam String userId, @RequestParam String phoneId){
				
		if(phoneService.setGCMData(userId, phoneId)){
			return "true";
		} else {
			return "false";
		}
		
	}
	
	/** 알림 설정 데이터 획득 */
	@RequestMapping(value="/alarmTime", method=RequestMethod.POST,
			produces="text/plain;charset=UTF-8")
	public @ResponseBody String notiSettingList(@RequestParam String userId){
		Gson gson = new Gson();
		List<NotificationSetting> settingList = null;
		try{
			settingList = phoneService.getNotificationSettingList(userId);
		} catch(DataAccessException e){
			return "false";
		}
		return gson.toJson(settingList);
	}
	
	/** 알림 출력 데이터 획득 */
	@RequestMapping(value="/alarmData", method=RequestMethod.POST, 
			produces="text/plain;charset=UTF-8")
	public @ResponseBody String notiDataList(@RequestParam String postingType){
		Gson gson = new Gson();
		
		Posting notiData = null;
		try{
			notiData = phoneService.getNotificationData(postingType);
		}catch(DataAccessException | WrongRequestException e){
			return "false";
		}
		return gson.toJson(notiData);
	}
}
