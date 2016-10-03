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

	/** 마일리지 이름. */
	private String mileName;

	/** 마일리지 값. */
	private Integer mileValue;

}
