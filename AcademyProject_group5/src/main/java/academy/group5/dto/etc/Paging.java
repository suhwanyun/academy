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
	private Object data;
	private Object data2;
	private Object data3;
	private Object data4;
	
	/** 정렬정보 */
	private String orderData;
	
	public Paging(int page, int size){
		this(page, size, null);
	}
	
	public Paging(int page, int size, Object data){
		this(page, size, data, null);
	}
	
	public Paging(int page, int size, Object data, Object data2){
		this(page, size, data, data2, null);
	}
	
	public Paging(int page, int size, Object data, Object data2, Object data3){
		this(page, size, data, data2, data3, null);
	}
	
	public Paging(int page, int size, Object data, Object data2, Object data3, Object data4){
		this.startIdx = (page - 1) * size + 1;
		this.endIdx = page * size;
		this.data = data;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
	}
}
