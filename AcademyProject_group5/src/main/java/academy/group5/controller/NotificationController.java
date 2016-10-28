package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import academy.group5.dto.etc.NotificationSettingList;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.NotificationService;
import academy.group5.util.Identify;

/**
 * 알림(설정) 컨트롤러
 * @author YSH
 *
 */
@Controller
public class NotificationController {
	
	@Autowired
	NotificationService service;
	
	Identify identify = new Identify();
	
	/** 알림 설정 목록 표시 */
	@RequestMapping(value="/noti/notiSettingjsp", method=RequestMethod.GET)
	public String notiSettingList(Model model, HttpSession session){
		// 로그인된 id 확인
		String id = identify.getUserId(session);
		model.addAttribute("settingData", service.getSettingList(id));

		return "noti/noti";
	}
	
	/** 알림 설정 */
	@RequestMapping(value="/noti/notiSetting", method=RequestMethod.POST)
	public String notiSetting(HttpSession session, RedirectAttributes redAttr,
			NotificationSettingList settingData){
		// 로그인된 id 확인
		String id = identify.getUserId(session);
		service.settingModify(id, settingData); 
		redAttr.addFlashAttribute("msg", "설정되었습니다.");
		redAttr.addFlashAttribute("nextJsp", "/noti/notiSettingjsp");

		return "redirect:/message";
	}
	
}
