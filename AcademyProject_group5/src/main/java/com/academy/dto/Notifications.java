package com.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {

	/** 알림 ID. */
	private Integer notiId;

	/** 알림 종류. */
	private String notiType;

	/** 회원 ID. */
	private String userId;

	/** 알림 제목. */
	private String notiTitle;

	/** 알림 내용. */
	private String notiContent;

}
