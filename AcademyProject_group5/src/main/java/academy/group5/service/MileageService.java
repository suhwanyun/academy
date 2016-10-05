package academy.group5.service;

import java.util.List;

import academy.group5.dto.Mileageproduct;

public interface MileageService {
	/**
	 * 상품의 전체리스트
	 * @return
	 */
	List<Mileageproduct> productList();
	/**
	 * 하나의 상품 내용
	 * @param productId
	 * @return
	 */
	Mileageproduct productOne(Integer productId);
	/**
	 * 회원의 구매목록
	 * @param userId
	 * @return
	 */
	List<Mileageproduct> shoppingList(String userId);
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
