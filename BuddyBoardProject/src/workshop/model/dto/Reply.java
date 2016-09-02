package workshop.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply {
	private int replyNo;
	private String replyContent;
	private Date regDate;
	private String customerCustId;
	private int boardBoardNo;

	public Reply(String replyContent, String customerCustId, int boardBoardNo) {
		super();
		this.replyContent = replyContent;
		this.customerCustId = customerCustId;
		this.boardBoardNo = boardBoardNo;
	}
	
	public Reply(String replyContent, int replyNo) {
		this.replyContent = replyContent;
		this.replyNo = replyNo;
	}
}
