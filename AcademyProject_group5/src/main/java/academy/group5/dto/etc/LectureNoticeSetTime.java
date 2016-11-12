package academy.group5.dto.etc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의시간 변경시 사용되는 데이터 모듈
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureNoticeSetTime {

	/** 강의 시간 ID */
	private Integer lectureTimeId;
	
	/** 강의 ID. */
	private Integer lectureId;

	/** 강의 분반. */
	private Integer lectureClass;

	/** 강의 시작시간. */
	private Integer lectureStart;

	/** 강의 종료시간. */
	private Integer lectureEnd;

	/** 강의 장소. */
	private String lecturePlace;
	
	/** 설정된 날짜. */
	private Date isTempDate;
	
	/** 공지 시간. */
	private String noticeTime;	
	
	/** 공지 종류. */
	private String noticeType;

	/** 공지 제목. */
	private String noticeTitle;

	/** 공지 내용. */
	private String noticeContent;
}
