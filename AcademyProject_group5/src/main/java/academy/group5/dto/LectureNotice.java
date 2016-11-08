package academy.group5.dto;

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
public class LectureNotice {

	/** 공지 시간. */
	private String noticeTime;

	/** 강의 ID. */
	private Integer lectureId;

	/** 강의 분반. */
	private Integer lectureClass;

	/** 공지 종류. */
	private String noticeType;

	/** 공지 제목. */
	private String noticeTitle;

	/** 공지 내용. */
	private String noticeContent;

	public LectureNotice(Date noticeTime, Integer lectureId, Integer lectureClass) {
		super();
		this.noticeTime = noticeTime;
		this.lectureId = lectureId;
		this.lectureClass = lectureClass;
	}
	
	
}
