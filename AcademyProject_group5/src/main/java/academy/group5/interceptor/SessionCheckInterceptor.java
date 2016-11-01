package academy.group5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import academy.group5.exception.PageRedirectException;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null){
			session.setAttribute("gotoPage", "/loginjsp");
			throw new PageRedirectException("로그인이 필요한 서비스입니다.");
		}
		return super.preHandle(request, response, handler);
	}
}
