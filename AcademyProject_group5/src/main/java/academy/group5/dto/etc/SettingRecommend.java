package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 추천에 사용되는 데이터 모듈
 * @author YSH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingRecommend {

	/** 게시글 ID. */
	private Integer postingId;

	/** 게시판 종류. */
	private String postingType;

	/** 추천 수 */
	private Integer postingRecommend;

	/**
	 * @param postingId
	 * @param postingType
	 */
	public SettingRecommend(Integer postingId, String postingType) {
		this(postingId, postingType, null);
	}
}
