package academy.group5.dto.etc;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import academy.group5.dto.LectureTime;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date isTempDate;
	
	/** 공지 종류. */
	private String noticeType;

	/** 공지 제목. */
	private String noticeTitle;

	/** 공지 내용. */
	private String noticeContent;
	
	public LectureNoticeSetTime(LectureTime timeData){
		this.lectureTimeId = timeData.getLectureTimeId();
		this.lectureId = timeData.getLectureId();
		this.lectureClass = timeData.getLectureClass();
		this.lectureStart = timeData.getLectureStart();
		this.lectureEnd = timeData.getLectureEnd();
		this.lecturePlace = timeData.getLecturePlace();
	}
}
