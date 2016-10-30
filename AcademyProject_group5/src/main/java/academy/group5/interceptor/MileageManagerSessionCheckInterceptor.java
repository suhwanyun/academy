package academy.group5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MileageManagerSessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		boolean isError = false;
		
		Object typeObj = session.getAttribute("managerType");
		if(typeObj == null){
			isError = true;
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
		} else if(!typeObj.equals("mileage")){
			isError = true;
			request.setAttribute("msg", "잘못된 접근입니다.");
		}

		if(isError){
			request.setAttribute("nextJsp", "/managerLoginjsp");
			request.getRequestDispatcher("/message").forward(request, response);
		}
		return super.preHandle(request, response, handler);
	}
}
