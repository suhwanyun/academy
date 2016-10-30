package academy.group5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LectureManagerSessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		Object typeObj = session.getAttribute("managerType");
		if(typeObj == null){
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
		} else if(!typeObj.equals("lecture")){
			request.setAttribute("msg", "잘못된 접근입니다.");
		}
		request.getRequestDispatcher("/managerLoginjsp").forward(request, response);
		
		return super.preHandle(request, response, handler);
	}
}
