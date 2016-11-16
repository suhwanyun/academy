package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 마일리지 상품 정보 전달에 사용되는 데이터 모듈
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMileageProduct {

	/** 상품 ID. */
	private Integer productId;

	/** 상품 이름. */
	private String productName;

	/** 상품 가격. */
	private Integer productCost;

	/** 상품 내용. */
	private String productContent;

	/** 이미지 파일명. */
	private String productImgfile;

	/** 남은 수량. */
	private Integer productRemain;

}
