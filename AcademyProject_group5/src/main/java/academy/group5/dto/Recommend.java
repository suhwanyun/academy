package academy.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 추천 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {

	/** 게시글 ID. */
	private Integer postingId;

	/** 게시판 종류. */
	private String postingType;

	/** 회원 ID. */
	private String userId;

	/**
	 * @param postingId
	 * @param postingType
	 */
	public Recommend(Integer postingId, String postingType) {
		this(postingId, postingType, null);
	}
}
