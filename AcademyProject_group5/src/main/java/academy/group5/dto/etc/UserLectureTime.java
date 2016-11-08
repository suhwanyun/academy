package academy.group5.dto.etc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 강의시간 중복 확인에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLectureTime {

	/** 회원 ID */
	private String userId;

	/** 강의 시작시간. */
	private Integer lectureStart;

	/** 강의 종료시간. */
	private Integer lectureEnd;

	/** 강의 요일. */
	private Integer lectureWeek;

	/** 강의 이름 */
	private String lectureName;
	
	/** 강의 ID */
	private Integer lectureId;
	
	/** 강의 분반 */
	private Integer lectureClass;
	
	/** 강의 장소 */
	private String lecturePlace;
	
	/** 반장 권한 여부. */
	private String isPresident;
	
	/** 권한종료 날짜. */
	private Date rightEndTime;
	
	public UserLectureTime(String userId, Integer lectureStart, Integer lectureEnd, Integer lectureWeek) {
		super();
		this.userId = userId;
		this.lectureStart = lectureStart;
		this.lectureEnd = lectureEnd;
		this.lectureWeek = lectureWeek;
	}
	
	
}
