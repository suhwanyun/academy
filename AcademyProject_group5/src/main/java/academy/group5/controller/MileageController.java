package academy.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MileageController {
	
	/** 구매 확인 페이지 */
	@RequestMapping(value="/mileage/confirm", method=RequestMethod.GET)
	public String confirm(){
		
		return "mileage/confirm";
	}
	
	/** 구매 물품 확인 페이지 */
	@RequestMapping(value="/mileage/product_list", method=RequestMethod.GET)
	public String productList(){
		
		return "mileage/product_list";
	}
	
	/** 구매 물품의 자세한 정보 확인 페이지 */
	@RequestMapping(value="/mileage/product", method=RequestMethod.GET)
	public String product(){
		
		return "mileage/product";
	}
}
