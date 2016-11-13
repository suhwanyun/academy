package academy.group5.dto;

import academy.group5.dto.etc.LectureNoticeSetTime;
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

	/** 공지 ID. */
	private Integer lectureNoticeId;
	
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

	public LectureNotice(Integer lectureId, Integer lectureClass, String noticeType, String noticeTitle,
			String noticeContent) {
		super();
		this.lectureId = lectureId;
		this.lectureClass = lectureClass;
		this.noticeType = noticeType;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}
	
	public LectureNotice(LectureNoticeSetTime lectureData) {
		super();
		this.lectureId = lectureData.getLectureId();
		this.lectureClass = lectureData.getLectureClass();
		this.noticeType = lectureData.getNoticeType();
		this.noticeTitle = lectureData.getNoticeTitle();
		this.noticeContent = lectureData.getNoticeContent();
	}
	
}
