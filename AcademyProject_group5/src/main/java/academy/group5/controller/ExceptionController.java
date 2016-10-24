package academy.group5.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 예외처리 컨트롤러
 * @author YSH
 *
 */
@Controller
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(PersistenceException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="PersistenceException")
	public void handlerException(Exception e){
		logger.trace("PersistenceException 내용: {}", e);
	}

}
