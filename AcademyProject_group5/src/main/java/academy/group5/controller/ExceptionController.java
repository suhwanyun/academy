package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import academy.group5.exception.LectureManagerException;
import academy.group5.exception.ManagerLoginException;
import academy.group5.exception.MileageManagerException;
import academy.group5.exception.WrongRequestException;
import academy.group5.service.ManagerService;

/**
 * 예외처리 컨트롤러
 * @author YSH
 *
 */
@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(DataAccessException.class)
	public String dbException(HttpSession session, Exception e){	
		logger.trace("\n\nDataAccessException 정보 :\n", e);
		
		Object isManagerObj = session.getAttribute("managerType");
		
		if(isManagerObj != null){		
			switch(isManagerObj.toString()){
			case ManagerService.TYPE_LECTURE:
				return "/error/lecture_manager_error_page";
			default :
				return "/error/mileage_manager_error_page";
			}
		}
		
		return "/error/error_page";
	}
	
	@ExceptionHandler(WrongRequestException.class)
	public String logicException(Exception e){	
		logger.trace("\n\nlogicException 정보 :\n", e);
		return "/error/error_page";
	}

	@ExceptionHandler(LectureManagerException.class)
	public String lectureManagerException(Exception e){	
		logger.trace("\n\nException 정보 :\n", e);
		
		return "/error/lecture_manager_error_page";
	}
	
	@ExceptionHandler(MileageManagerException.class)
	public String mileageManagerException(Exception e){	
		logger.trace("\n\nException 정보 :\n", e);
		
		return "/error/mileage_manager_error_page";
	}
	
	@ExceptionHandler(ManagerLoginException.class)
	public String managerLoginException(Exception e){	
		logger.trace("\n\nException 정보 :\n", e);
		return "/managerLoginjsp";
	}
}
