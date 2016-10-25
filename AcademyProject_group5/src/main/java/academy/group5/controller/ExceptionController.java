package academy.group5.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import academy.group5.exception.WrongRequestException;

/**
 * 예외처리 컨트롤러
 * @author YSH
 *
 */
@Controller
public class ExceptionController {
	
	@ExceptionHandler({	PersistenceException.class,
						DuplicateKeyException.class})
	public String dbException(Exception e){	
		return "/error/error_page";
	}

}
