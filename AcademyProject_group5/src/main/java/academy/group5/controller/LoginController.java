package academy.group5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import academy.group5.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/findUser", method=RequestMethod.GET)
	public @ResponseBody boolean findUser(Model model, @RequestParam String userId){
		
		return service.findUser(userId);
	}
}
