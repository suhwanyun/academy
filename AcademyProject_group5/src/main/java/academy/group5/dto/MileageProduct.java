package academy.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 마일리지 상품 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MileageProduct {

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

	/** 보유 마일리지 상품 목록. *//*
	private Set<Usermileage> usermileageSet;*/
	
	public MileageProduct(String productName, Integer productCost, String productContent){
		this(productName, productCost, productContent, null);
	}
	
	public MileageProduct(String productName, Integer productCost, String productContent, String productImgfile) {
		super();
		this.productName = productName;
		this.productCost = productCost;
		this.productContent = productContent;
		this.productImgfile = productImgfile;
	}

}
