package academy.group5.dto.etc;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 페이징에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@NoArgsConstructor
public class LecturePaging {
	
	/** 시작 인덱스 */
	private Integer startIdx;

	/** 종료 인덱스 */
	private Integer endIdx;
	
	/** 부가 데이터 */
	private Integer lectureId;
	private Integer lectureClass;
	private String userId;
	
	public LecturePaging(int page, int size, String userId){
		this(page, size, userId, null, null);
	}
	
	public LecturePaging(int page, int size, Integer lectureId, Integer lectureClass){
		this(page, size, null, lectureId, lectureClass);
	}

	public LecturePaging(int page, int size, String userId, Integer lectureId, Integer lectureClass){
		this.startIdx = (page - 1) * size + 1;
		this.endIdx = page * size;
		this.userId = userId;
		this.lectureId = lectureId;
		this.lectureClass = lectureClass;
	}
}
