package academy.group5.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 게시글 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posting {

	/** 게시글 ID. */
	private Integer postingId;

	/** 게시판 종류. */
	private String postingType;

	/** 회원 ID. */
	private String userId;

	/** 게시글 등록 날짜. */
	private String postingTime;

	/** 게시판 조회수. */
	private Integer postingHits;

	/** 게시판 추천수. */
	private Integer postingRecommand;

	/** 게시글 제목. */
	private String postingTitle;

	/** 게시글 내용. */
	private String postingContent;

	/** 이미지 파일명. */
	private String postingPhoto;

	/** 댓글 목록. *//*
	private Set<PostingComment> commentSet;

	*//** 추천 목록. *//*
	private Set<Recommend> recommendSet;*/

	public Posting(String postingType, String userId, String postingTitle, String postingContent) {
		super();
		this.postingType = postingType;
		this.userId = userId;
		this.postingTitle = postingTitle;
		this.postingContent = postingContent;
	}
}
