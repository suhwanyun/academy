package academy.group5.dto.etc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 날짜 formatting에 사용되는 데이터 모음
 * @author YSH
 *
 */

public class DateDto {
	
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
