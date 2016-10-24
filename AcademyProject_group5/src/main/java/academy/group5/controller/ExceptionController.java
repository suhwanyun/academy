package academy.group5.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 예외처리 컨트롤러
 * @author YSH
 *
 */
@Controller
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler({PersistenceException.class, DuplicateKeyException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="DBException")
	public void handlerException(Exception e){
		logger.trace("\n----Exception 내용 출력----\n{}", e);
	}

}
