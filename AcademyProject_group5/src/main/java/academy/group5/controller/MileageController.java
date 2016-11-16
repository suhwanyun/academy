package academy.group5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import academy.group5.dto.MileageProduct;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.UserMileageProduct;
import academy.group5.exception.PageRedirectException;
import academy.group5.service.MileageService;
import academy.group5.util.Identify;

/**
 * 마일리지 관리 컨트롤러
 * @author YSH
 *
 */
@Controller
public class MileageController {
	
	@Autowired
	MileageService mileageService;
	
	Identify identify = new Identify();
	
	/** 마일리지 물품의 자세한 정보 확인 */
	@RequestMapping(value="/mileage/productBuyjsp", method=RequestMethod.GET)
	public String product(HttpSession session, Model model,
			@RequestParam int productId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/mileageMain");
				
		MileageProduct productData = mileageService.productOne(productId);
		model.addAttribute("productData", productData);
		
		return "mileage/product";
	}
	
	/** 물품 구매 */
	@RequestMapping(value="/mileage/productBuy", method=RequestMethod.GET)
	public String buyProduct(HttpSession session, Model model,
			@RequestParam int productId){
		
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/mileage/productBuyjsp?productId=" + productId);
		session.setAttribute("gotoPage", "/mileage/productBuyjsp?productId=" + productId);
		// 회원 ID확인
		String userId = identify.getUserId(session);
		
		UserData newUserData = mileageService.buyProduct(userId, productId);
		// 줄어든 마일리지 반영
		session.setAttribute("user", newUserData);
			
		throw new PageRedirectException("구입하신 상품은 메뉴-보관함에서 확인하실 수 있습니다.");
	}
	
	/** 구매 물품 확인 페이지 */
	@RequestMapping(value="/mileage/productUsejsp", method=RequestMethod.GET)
	public String productList(HttpSession session, Model model,
			@RequestParam int productId){
		// 에러 발생시 이동할 페이지
		session.setAttribute("errorGotoPage", "/mileage/myMileageMain");
		// 회원 ID확인
		String userId = identify.getUserId(session);
		// 상품 정보(수량 포함)
		UserMileageProduct productData = mileageService.productOneByUser(userId, productId);
		model.addAttribute("productData", productData);
		// 물품 사용페이지 플래그 설정
		model.addAttribute("isMine", "ture");
		
		return "mileage/product";
	}
	
	/** 구매한 물품의 사용 */
	@RequestMapping(value="/mileage/productUse", method=RequestMethod.GET)
	public String myProduct(HttpSession session, Model model,
			@RequestParam int productId){
		
		// 에러 발생시 / 작업 완료시 이동할 페이지
		session.setAttribute("errorGotoPage", "/mileage/productUsejsp?productId="+productId);
		session.setAttribute("gotoPage", "/mileage/myMileageMain");
		// 회원 ID확인
		String userId = identify.getUserId(session);
		// 상품 사용		
		mileageService.useProduct(userId, productId);
		
		throw new PageRedirectException("이용해주셔서 감사합니다.");
	}
	
}
