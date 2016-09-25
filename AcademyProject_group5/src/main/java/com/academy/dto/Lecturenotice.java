package com.academy.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의 공지 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecturenotice {

	/** 공지 시간. */
	private Date noticeTime;

	/** 강의. */
	private Lecture lecture;

	/** 공지 종류. */
	private String noticeType;

	/** 공지 제목. */
	private String noticeTitle;

	/** 공지 내용. */
	private String noticeContent;
}
