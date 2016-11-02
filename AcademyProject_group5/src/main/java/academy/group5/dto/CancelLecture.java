package academy.group5.dto;

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

	/** 휴강 날짜. */
	private Date cancelDate;
	
	/** 강의 시간 ID. */
	private Integer lectureTimeId;
}
