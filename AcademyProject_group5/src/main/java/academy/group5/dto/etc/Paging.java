package academy.group5.dto.etc;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 페이징에 사용되는 데이터 모음
 * @author YSH
 *
 */
@Data
@NoArgsConstructor
public class Paging {
	/** 시작 인덱스 */
	private Integer startIdx;

	/** 종료 인덱스 */
	private Integer endIdx;
	
	/** 부가 데이터 */
	private String data;
	
	public Paging(int page, int size){
		this(page, size, null);
	}
	
	public Paging(int page, int size, String data){
		this.startIdx = (page - 1) * size + 1;
		this.endIdx = page * size;
		this.data = data;
	}
}
