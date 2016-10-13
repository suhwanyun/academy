package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 반장 투표에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voting {
	
	/** 강의 ID. */
	private Integer lectureId;

	/** 반장 선출 결과 */
	private Integer votingResult;
	
}
