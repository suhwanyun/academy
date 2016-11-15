package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 핸드폰에 날짜 정보 전달시 사용되는 데이터 모듈
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermForPhone {
	
	/** 학기시작 날짜. */
	private String termStart;

	/** 학기종료 날짜. */
	private String termEnd;

}
