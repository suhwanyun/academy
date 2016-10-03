package academy.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 관리자 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

	/** 관리자 ID. */
	private String managerId;

	/** 관리자 암호. */
	private String managerPass;

	/** 관리자 종류. */
	private String managerType;
	
}
