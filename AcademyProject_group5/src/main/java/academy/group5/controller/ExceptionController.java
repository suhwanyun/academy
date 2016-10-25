package academy.group5.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import academy.group5.exception.WrongRequestException;

/**
 * 예외처리 컨트롤러
 * @author YSH
 *
 */
@Controller
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(DataAccessException.class)
	public String dbException(Exception e){	
		logger.trace("\n\nDataAccessException 정보 :\n{}\n", e);
		return "/error/error_page";
	}
	
	@ExceptionHandler(WrongRequestException.class)
	public String logicException(Exception e){	
		logger.trace("\n\nlogicException 정보 :\n{}\n", e);
		return "/error/error_page_logic";
	}

}
