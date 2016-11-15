package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의 알림 설정에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureTimeForPhone {

	/** 강의 시간 ID */
	private Integer lectureTimeId;
	
	/** 강의 이름 */
	private String lectureName;
	
	/** 교수 이름. */
	private String professorName;
	
	/** 강의 요일. */
	private Integer lectureWeek;
	
	/** 강의 시작시간. */
	private Integer lectureStart;
	
	/** 강의 장소 */
	private String lecturePlace;
	
	/** 임시 등록 날짜. */
	private String isTempDate;
}
