package workshop.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
	private int boardNo;
	private Date regDate;
	private String content;
	private String customerCustId;
	
	public Board(String content, String customerCustId) {
		super();
		this.content = content;
		this.customerCustId = customerCustId;
	}
	
	public Board(int boardNo, String content) {
		super();
		this.boardNo = boardNo;
		this.content = content;
	}
	
}
