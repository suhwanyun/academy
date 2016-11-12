package academy.group5.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 학기 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Term {
	
	/** 학기시작 날짜. */
	private Date termStart;

	/** 학기종료 날짜. */
	private Date termEnd;

}
