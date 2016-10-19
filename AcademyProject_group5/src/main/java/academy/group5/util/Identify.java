package academy.group5.util;

import javax.servlet.http.HttpSession;

import academy.group5.dto.UserData;
import academy.group5.exception.SessionNotFoundException;

public class Identify {
	
	/** 로그인된 id 확인 */
	public String getUserId(HttpSession session){
		Object userAttrObj = session.getAttribute("user");
		if(userAttrObj != null){
			return ((UserData)userAttrObj).getUserId();
		}else {		
			throw new SessionNotFoundException();		
		}
	}
}
