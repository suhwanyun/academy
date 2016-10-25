package academy.group5.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import academy.group5.dto.Posting;
import academy.group5.dto.PostingComment;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;

public interface PostingService {
	
	/** 기본 이미지 */
	public final String DEFAULT_PHOTO_NAME = "default.png";
	
	/**
	 * 게시글 리스트 불러오기
	 * @param page
	 * @param postingType
	 * @param searchData
	 * @param searchType
	 * @return
	 */
	List<Posting> postingList(int page, String postingType, String orderData, String searchData, String searchType);
	
	List<Posting> postingList(int page, String postingType);
	
	/**
	 * 가장 추천을 많이 받은 게시글
	 * @return
	 */
	Posting mostRecommend(MostRecommend searchData);
	/**
	 * 게시글 쓰기
	 * @param posting
	 * @return
	 */
	boolean postWrite(Posting posting);
	/**
	 * 게시글 수정
	 * @param posting
	 * @return
	 */
	boolean postModify(Posting posting);
	/**
	 * 작성한 게시글 id 확인
	 * @param posting
	 * @return
	 */
	Integer getPostingId(Posting posting);
	/**
	 * 작성한 글에 이미지 등록
	 * @param posting
	 * @return
	 */
	boolean photoRegister(Posting posting);
	/**
	 * 이미지 업로드 처리
	 * @param uploadData
	 * @param postingData
	 * @return
	 */
	int upload(MultipartFile uploadData, Posting postingData);
	
	/**
	 * 이미지 삭제 처리
	 * @param postingData
	 * @return
	 */
	void uploadCancel(Posting postingData, String defaultName);
	/**
	 * 게시글 삭제
	 * @param postingId
	 * @param postingType
	 * @return
	 */
	boolean postDelete(String userId, int postingId, String postingType);
	/**
	 * 게시글 보기
	 * @param postingId
	 * @param postingType
	 * @return
	 */
	Posting postView(Integer postingId, String postingType);
	/**
	 * 댓글 목록 가져오기
	 * @param postingId
	 * @param postingType
	 * @return
	 */
	Map<String, List<PostingComment>> commentList(Integer postingId, String postingType);
	/**
	 * 댓글 작성
	 * @param comment
	 * @return
	 */
	boolean commentWrite(PostingComment comment);
	/**
	 * 댓글 삭제
	 * @param commentId
	 * @return
	 */
	boolean commentDelete(Integer commentId);
	/**
	 * 댓글 수정
	 * @param comment
	 * @return
	 */
	boolean commentModify(PostingComment comment);
	
	/**
	 * 게시글 추천수 확인
	 * @param postingId
	 * @param postingType
	 * @return
	 */
	Integer getRecommendsCount(Integer postingId, String postingType);
	
	/**
	 * 추천
	 * @param postingId
	 * @param postingType
	 * @param userId
	 * @return 0:정상 1:이미추천 -1:탈퇴한 회원의 게시글
	 */
	boolean setRecommend(Integer postingId, String postingType, String userId);
	
}
