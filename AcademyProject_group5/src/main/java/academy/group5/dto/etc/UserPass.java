package academy.group5.dto.etc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PASSWORD 찾기에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPass {
	/** 회원 ID. */
	private String userId;

	/** 비밀번호 질문 답. */
	private String passAnswer;
}
