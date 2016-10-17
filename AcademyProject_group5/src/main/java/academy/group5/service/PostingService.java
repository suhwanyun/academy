package academy.group5.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import academy.group5.dto.Posting;
import academy.group5.dto.PostingComment;

public interface PostingService {
	/**
	 * 게시글 리스트 불러오기
	 * @param page
	 * @param postingType
	 * @param searchData
	 * @param searchType
	 * @return
	 */
	List<Posting> postingList(int page, String postingType, String searchData, String searchType);
	
	List<Posting> postingList(int page, String postingType);
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
	 * 게시글 삭제
	 * @param postingId
	 * @param postingType
	 * @return
	 */
	boolean postDelete(Integer postingId, String postingType);
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
	List<PostingComment> commentList(Integer postingId, String postingType);
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
	 * 추천 서비스(1.등록이된지 확인하고 2.등록이 되지않았다면 등록하고)
	 * @param postingId
	 * @param postingType
	 * @param userId
	 * @return
	 */
	boolean recommend(Integer postingId, String postingType, String userId);
	
}
