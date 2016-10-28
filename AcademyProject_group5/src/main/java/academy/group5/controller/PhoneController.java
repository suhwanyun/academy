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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.repo.GCMRepo;
import academy.group5.service.LoginService;
import academy.group5.service.PhoneService;

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
	public String login(HttpSession session, RedirectAttributes redAttr,
			@RequestParam String userId, @RequestParam String userPass){
		
		UserData data = loginService.login(userId, userPass);
				
		if(data != null){	
			session.setAttribute("user", data);	
		} else {
			redAttr.addFlashAttribute("msg", "로그인에 실패하였습니다.");
			redAttr.addFlashAttribute("nextJsp", "/main");
			return "redirect:/message";
		}
		
		return "index";
	}
	
	/** GCM 등록 */
	@RequestMapping(value="/registGCM", method=RequestMethod.POST)
	public @ResponseBody String addFood(@RequestParam String userId, @RequestParam String phoneId){
		int result = phoneService.setGCMData(userId, phoneId);

		if(result != 1){
			return "false";
		}
		return "true";
	}
	
	/** 알림 설정 데이터 획득 */
	@RequestMapping(value="/alarmTime", method=RequestMethod.POST)
	public @ResponseBody String notiSettingList(@RequestParam String userId){
		Gson gson = new Gson();
		List<NotificationSetting> settingList = phoneService.getNotificationSettingList(userId);
		
		if(settingList == null){
			return "false";
		}
		return gson.toJson(settingList);
	}
	
	/** 알림 출력 데이터 획득 */
	@RequestMapping(value="/alarmData", method=RequestMethod.POST)
	public @ResponseBody String notiDataList(@RequestParam String postingType){
		Gson gson = new Gson();
		Posting notiData = phoneService.getNotificationData(postingType);
		
		if(notiData == null){
			return "false";
		}
		return gson.toJson(notiData);
	}
}
