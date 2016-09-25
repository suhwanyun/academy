package com.academy.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 휴강 모델 클래스.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelLecture {

	/** 강의 ID. */
	private Integer lectureId;

	/** 강의 분반. */
	private Integer lectureClass;

	/** 강의 시작시간. */
	private Integer lectureStart;
	
	/** 휴강 날짜. */
	private Date cancelDate;

}
