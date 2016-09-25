package com.academy.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림설정 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificationsetting {

	/** 알림 종류. */
	private String notiType;

	/** 회원 ID. */
	private String userId;

	/** 알림 여부. */
	private Integer notiOn;

	/** 알림 시간. */
	private Date notiTime;

	/** 알림 간격. */
	private Integer notiTimeInterval;

	/** 알림 목록. */
	private Set<Notifications> notificationsSet;

}
