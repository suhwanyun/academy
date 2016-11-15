package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 핸드폰에 휴강 날짜 전달시 사용되는 데이터 모듈
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureCancelForPhone {
	
	/** 휴강 날짜. */
	private String cancelDate;

}
