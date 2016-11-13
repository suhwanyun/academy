package academy.group5.dto;

import java.util.Calendar;
import java.util.Date;

import academy.group5.dto.etc.LectureNoticeSetTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의시간 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureTime {

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

	/** 강의 요일. */
	private Integer lectureWeek;

	/** 임시 등록 날짜. */
	private Date isTempDate;

	/** 휴강 목록. *//*
	private Set<CancelLecture> cancelSet;*/

	public LectureTime(Integer lectureId, Integer lectureClass) {
		super();
		this.lectureId = lectureId;
		this.lectureClass = lectureClass;
	}
	
	public LectureTime(LectureNoticeSetTime timeData) {
		super();
		this.lectureTimeId = timeData.getLectureTimeId();
		this.lectureStart = timeData.getLectureStart();
		this.lectureEnd = timeData.getLectureEnd();
		this.lecturePlace = timeData.getLecturePlace();
		this.isTempDate = timeData.getIsTempDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(timeData.getIsTempDate());
		this.lectureWeek = cal.get(Calendar.DAY_OF_WEEK);
	}
}
