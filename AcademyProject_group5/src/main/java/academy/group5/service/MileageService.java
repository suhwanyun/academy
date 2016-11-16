package academy.group5.service;

import java.util.List;

import academy.group5.dto.MileageProduct;
import academy.group5.dto.etc.UserMileageProduct;

public interface MileageService {
	/**
	 * 상품의 전체리스트
	 * @return
	 */
	List<MileageProduct> productList();
	/**
	 * 하나의 상품 내용
	 * @param productId
	 * @return
	 */
	MileageProduct productOne(Integer productId);
	/**
	 * 하나의 상품 내용(수량 포함)
	 * @param userId
	 * @param productId
	 * @return
	 */
	UserMileageProduct productOneByUser(String userId, Integer productId);
	/**
	 * 회원의 구매목록
	 * @param userId
	 * @return
	 */
	List<MileageProduct> shoppingList(String userId);
	/**
	 * 구매하기
	 * @param userId
	 * @param productId
	 * @return
	 */
	boolean buyProduct(String userId, Integer productId);
	/**
	 * 상품 사용하기
	 * @param userId
	 * @param productId
	 * @return
	 */
	boolean useProduct(String userId, Integer productId);
}
