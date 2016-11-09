package academy.group5.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의 신청 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureApply {

	/** 강의 ID. */
	private Integer lectureId;

	/** 회원 ID. */
	private String userId;
	
	/** 강의 분반. */
	private Integer lectureClass;

	/** 반장 권한 여부. */
	private String isPresident;

	/** 권한종료 날짜. */
	private Date rightEndTime;

	public LectureApply(Integer lectureId, String userId, Integer lectureClass) {
		super();
		this.lectureId = lectureId;
		this.userId = userId;
		this.lectureClass = lectureClass;
	}
}
