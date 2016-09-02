package workshop.model.dto;
// Java Bean
// 모든 멤버변수는 private, 변수에 접근할 setter, getter, default constructor 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
	private String custId;
	private String custName;
	private String custPass;
}
