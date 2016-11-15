package academy.group5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.Term;
import academy.group5.dto.etc.LectureTimeForPhone;
import academy.group5.exception.WrongRequestException;
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
	
	/** GCM 등록 */
	@RequestMapping(value="/registGCM", method=RequestMethod.POST)
	public @ResponseBody String addFood(@RequestParam String userId, @RequestParam String phoneId){
				
		if(phoneService.setGCMData(userId, phoneId)){
			return "true";
		} else {
			return "false";
		}
		
	}
	
	/** 학기 정보 데이터 획득 */
	@RequestMapping(value="/termTime", method=RequestMethod.POST)
	public @ResponseBody String termData(){
		Gson gson = new Gson();
		Term termData = null;
		try{
			termData = phoneService.getTermData();
		} catch(DataAccessException e){
			return "false";
		}
		if(termData == null){
			return "false";
		}
		
		return gson.toJson(termData);
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
	
	/** 강의 알림 데이터 획득 */
	@RequestMapping(value="/lectureData", method=RequestMethod.POST, 
			produces="text/plain;charset=UTF-8")
	public @ResponseBody String lectureDataList(@RequestParam String userId){
		Gson gson = new Gson();
		
		List<LectureTimeForPhone> lectureData = null;
		try{
			lectureData = phoneService.getLectureTimeList(userId);
		}catch(DataAccessException e){
			return "false";
		}
		return gson.toJson(lectureData);
	}
}
