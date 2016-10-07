package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		logger.trace("pathinfo: {}, requestURI: {}", request.getPathInfo(), request.getRequestURI());
		if(session.getAttribute("user") == null){
			response.sendRedirect(request.getContextPath() + "/main");
		}
		return super.preHandle(request, response, handler);
	}
}
