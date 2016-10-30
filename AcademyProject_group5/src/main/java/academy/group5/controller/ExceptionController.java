package academy.group5.controller;

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

/**
 * 예외처리 컨트롤러
 * @author YSH
 *
 */
@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(DataAccessException.class)
	public String dbException(Exception e){	
		logger.trace("\n\nDataAccessException 정보 :\n", e);
		return "/error/error_page";
	}
	
	@ExceptionHandler(WrongRequestException.class)
	public String logicException(Exception e){	
		logger.trace("\n\nlogicException 정보 :\n", e);
		return "/error/error_page_logic";
	}

	@ExceptionHandler(LectureManagerException.class)
	public ModelAndView lectureManagerException(Exception e){	
		logger.trace("\n\nException 정보 :\n", e);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error/error_page_logic");
		mav.addObject("goto", "/manage/lectureMain");
		return mav;
	}
	
	@ExceptionHandler(MileageManagerException.class)
	public ModelAndView mileageManagerException(Exception e){	
		logger.trace("\n\nException 정보 :\n", e);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error/error_page_logic");
		mav.addObject("goto", "/manage/mileageMain");
		return mav;
	}
	
	@ExceptionHandler(ManagerLoginException.class)
	public String managerLoginException(Exception e){	
		logger.trace("\n\nException 정보 :\n", e);
		return "/managerLoginjsp";
	}
}
