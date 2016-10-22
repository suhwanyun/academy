package academy.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
	
	/** 회원 ID. */
	private String userId;

	/** 회원 암호. */
	private String userPass;

	/** 회원 이름. */
	private String userName;

	/** 회원 마일리지. */
	private Integer userMileage;

	/** 핸드폰 ID. */
	private String phoneId;

	/** 핸드폰 번호. */
	private String phoneNum;

	/** 비밀번호 질문. */
	private String passQuestion;

	/** 비밀번호 질문 답. */
	private String passAnswer;

	/** 댓글 목록. *//*
	private Set<PostingComment> commentSet;

	*//** 강의 신청 목록. *//*
	private Set<Lectureapply> lectureapplySet;

	*//** 알림설정 목록. *//*
	private Set<Notificationsetting> notificationsettingSet;

	*//** 게시글 목록. *//*
	private Set<Posting> postingSet;

	*//** 추천 목록. *//*
	private Set<Recommend> recommendSet;

	*//** 보유 마일리지 상품 목록. *//*
	private Set<Usermileage> usermileageSet;*/	

	public UserData(String userId, String userPass){
		this(userId, userPass, null);
	}
	
	public UserData(String userId, String userPass, String phoneId){
		this.userId = userId;
		this.userPass = userPass;
		this.phoneId = phoneId;
	}
}
