package com.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 보유 마일리지 상품 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usermileage {

	/** 회원 ID. */
	private String userId;
	
	/** 상품 ID. */
	private Integer productId;

	/** 남은 수량. */
	private Integer productRemain;

}
