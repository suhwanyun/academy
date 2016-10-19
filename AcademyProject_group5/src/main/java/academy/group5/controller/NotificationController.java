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

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.UserData;
import academy.group5.service.NotificationService;
import academy.group5.util.Identify;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService service;
	
	Identify identify = new Identify();
	
	/** 알림 설정 목록 표시 */
	@RequestMapping(value="/noti/notiSettingList", method=RequestMethod.GET)
	public String notiSettingList(Model model, HttpSession session){
		// 로그인된 id 확인
		String id = identify.getUserId(session);
		
		if(id == null || id.equals("")){
			return "index";
		}
		
		List<NotificationSetting> settingList = service.settingList(id);
		model.addAttribute("settingList", settingList);
		
		return "noti/noti";
	}
	
	/** 알림 설정 */
	@RequestMapping(value="/noti/notiSetting", method=RequestMethod.GET)
	public @ResponseBody String notiSetting(Model model, @RequestParam List<NotificationSetting> settingList){
		
		int successCount = 0;
		for(NotificationSetting setting : settingList){
			if(service.settingModify(setting)){
				successCount++;
			}
		}
		
		if(successCount < settingList.size()){
			return "false";
		}
		
		//service.getNotificationSettingList();
		return "true";
	}
	
}
