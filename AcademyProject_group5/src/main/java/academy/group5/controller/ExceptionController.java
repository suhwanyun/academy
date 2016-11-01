package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import academy.group5.exception.PageRedirectException;
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
	public ModelAndView dbException(HttpSession session, Exception e){	
		logger.trace("\n\nDataAccessException 정보 :\n", e);
		session.removeAttribute("errorGotoPage");
		return getModelAndView("/error/error_page", "오류가 발생하였습니다.\\n잠시 후 다시 시도해주세요.");		
	}
	
	@ExceptionHandler(WrongRequestException.class)
	public ModelAndView logicException(Exception e){	
		
		return getModelAndView("/error/error_page", e.getMessage());
	}
	
	@ExceptionHandler(PageRedirectException.class)
	public ModelAndView moveException(Exception e){	
		
		return getModelAndView("/message", e.getMessage());
	}
	
	private ModelAndView getModelAndView(String viewName, String msg){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		if(msg != null){
			mav.addObject("msg", msg);
		}
		return mav;
	}
}
