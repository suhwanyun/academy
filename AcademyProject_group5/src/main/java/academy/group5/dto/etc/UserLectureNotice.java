package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의 공지 리스트 출력에 사용되는 데이터 모음
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLectureNotice {

	/** 공지 ID. */
	private Integer lectureNoticeId;
	
	/** 공지 시간. */
	private String noticeTime;
	
	/** 강의 ID */
	private Integer lectureId;
	
	/** 강의 분반 */
	private Integer lectureClass;
	
	/** 강의 이름 */
	private String lectureName;

	/** 공지 제목. */
	private String noticeTitle;
}
