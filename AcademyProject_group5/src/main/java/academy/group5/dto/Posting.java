package academy.group5.dto;

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
	
	/** 게시판 종류 */
	public final static String TYPE_FOOD = "food";
	public final static String TYPE_PLAY = "play";
	public final static String TYPE_PLACE = "place";
	public final static String TYPE_LECTURE = "lecture";

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
	private Integer postingRecommend;

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

	public Posting(Integer postingId, String postingType){
		this(null, postingId, postingType);
	}
	
	public Posting(String userId, Integer postingId, String postingType){
		this.userId = userId;
		this.postingId = postingId;
		this.postingType = postingType;
	}
	
	public Posting(String postingType, String userId, 
			String postingTitle, String postingContent, String postingPhoto) {
		super();
		this.postingType = postingType;
		this.userId = userId;
		this.postingTitle = postingTitle;
		this.postingContent = postingContent;
		this.postingPhoto = postingPhoto;
	}
}
