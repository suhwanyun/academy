package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import academy.group5.dto.UserData;
import academy.group5.service.NotificationService;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService service;
	
	/** 알림 설정 목록 표시 */
	@RequestMapping(value="/noti/settingList", method=RequestMethod.GET)
	public String userNotiList(Model model, HttpSession session){
		// 로그인된 id 확인
		String id = ((UserData)session.getAttribute("user")).getUserId();
		if(id == null || id.equals("")){
			return "index";
		}
		//service.getNotificationSettingList();
		return "noti/noti";
	}
	
}
