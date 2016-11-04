package academy.group5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import academy.group5.exception.PageRedirectException;

public class LectureManagerSessionCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		Object typeObj = session.getAttribute("managerType");
		if(typeObj == null){
			request.setAttribute("gotoPage", "/managerLoginjsp");
			throw new PageRedirectException("로그인이 필요한 서비스입니다.");
		} 
		else if(!typeObj.equals("lecture")){
			session.removeAttribute("isManage");
			session.removeAttribute("managerType");
			request.setAttribute("gotoPage", "/managerLoginjsp");
			throw new PageRedirectException("잘못된 접근입니다.");
		} 
		else if(session.getAttribute("user") != null){
			session.removeAttribute("user");
			session.setAttribute("gotoPage", "/lectureManage/main");
			throw new PageRedirectException("관리자 로그인이 감지되어 일반 회원 로그인을 해지합니다.");
		}
		return super.preHandle(request, response, handler);
	}
}
