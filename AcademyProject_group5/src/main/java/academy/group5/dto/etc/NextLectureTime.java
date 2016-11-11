package academy.group5.dto.etc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의 시간 수정에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NextLectureTime {

	/** 강의 시간 ID */
	private Integer lectureTimeId;
	
	/** 다음 강의 날짜. */
	private Date nextLectureTime;
	
	/** 강의 시작시간. */
	private Integer lectureStart;

	/** 강의 종료시간. */
	private Integer lectureEnd;
}
