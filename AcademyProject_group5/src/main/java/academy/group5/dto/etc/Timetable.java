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
public class Timetable {
	/** 시작 인덱스 */
	private Integer startIdx;

	/** 종료 인덱스 */
	private Integer endIdx;
	
	public Timetable(int page, int size){
		this.startIdx = (page - 1) * size + 1;
		this.endIdx = page * size;
	}
}
