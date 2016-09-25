package com.academy.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 학기 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Term {

	/** 년도. */
	private Integer termYear;

	/** 학기 구분. */
	private Integer termClassify;

	/** 학기시작 날짜. */
	private Date termStart;

	/** 학기종료 날짜. */
	private Date termEnd;

}
