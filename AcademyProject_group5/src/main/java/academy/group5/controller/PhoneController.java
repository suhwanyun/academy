package academy.group5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import academy.group5.repo.GCMRepo;
import academy.group5.service.PhoneService;
import academy.group5.util.GCM;

@Controller
public class PhoneController {
	
	@Autowired
	PhoneService phoneService;
	
	@Autowired
	GCMRepo gcmRepo;
	
	/** 식사(먹거리)추천 게시판에 글 작성 */
	@RequestMapping(value="/registGCM", method=RequestMethod.POST)
	public @ResponseBody String addFood(@RequestParam String userId, @RequestParam String phoneId){
		int result = phoneService.setGCMData(userId, phoneId);

		if(result != 1){
			return "false";
		}
		return "true";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(){
		new GCM("테스트입니다", "테스트 메세지를 출력합니다 / 테스트 메세지를 출력합니다 / 테스트 메세지를 출력합니다", gcmRepo.getAllUser(), GCM.TYPE_NOTI);
		return "index";
	}
	
	
}
