package academy.group5.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 댓글 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingComment {

	/** 댓글 ID. */
	private Integer commentId;

	/** 게시글 ID. */
	private Integer postingId;

	/** 게시판 종류. */
	private String postingType;

	/** 회원 ID. */
	private String userId;

	/** 부모 댓글 ID. */
	private Integer commentParentId;

	/** 댓글등록 날짜. */
	private Date commentTime;

	/** 댓글 내용. */
	private String commentContent;

	/** 자식 댓글 목록. */
	private Set<PostingComment> commentSet;

}
