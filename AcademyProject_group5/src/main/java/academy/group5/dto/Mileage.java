package academy.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 마일리지 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mileage {

	/** 가장 추천수 높은 게시글 마일리지 획득량 */
	public static final int MILEAGE_MOST_RECOMMEND_DAY = 1000;
	public static final int MILEAGE_MOST_RECOMMEND_WEEK = 200;
	
	/** 마일리지 이름. */
	private String mileName;

	/** 마일리지 값. */
	private Integer mileValue;

}
