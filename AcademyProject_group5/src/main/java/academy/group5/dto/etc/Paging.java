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
	private String postingType;
	private String searchData;
	private String searchDataType;
	private String orderType;
	private boolean isAsc;
	
	public Paging(String searchData, String searchDataType){
		this.searchData = searchData;
		this.searchDataType = searchDataType;
	}
	public Paging(int page, int size){
		this(page, size, null);
	}
	
	public Paging(int page, int size, String postingType){
		this(page, size, postingType, null, null, null);
	}
	
	public Paging(int page, int size, String searchData, String searchDataType){
		this(page, size, null, searchData, searchDataType, null);
	}

	public Paging(int page, int size, String postingType, String searchData, String searchDataType, String orderType){
		this(page, size, null, searchData, searchDataType, orderType, true);
	}
	
	public Paging(int page, int size, String postingType, String searchData, String searchDataType, String orderType, boolean isAsc){
		this.startIdx = (page - 1) * size + 1;
		this.endIdx = page * size;
		this.postingType = postingType;
		this.searchData = searchData;
		this.searchDataType = searchDataType;
		this.orderType = orderType;
		this.isAsc = isAsc;
	}
}
