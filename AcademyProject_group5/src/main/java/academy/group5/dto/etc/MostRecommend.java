package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 추천을 제일 많이 받은 게시글 찾기에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MostRecommend {
	
	/** 검색 기간(하루 단위) */
	private int period;

	/** 게시판 종류. */
	private String postingType;
	
}
