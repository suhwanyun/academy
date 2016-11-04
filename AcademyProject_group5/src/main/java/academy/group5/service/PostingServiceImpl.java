package academy.group5.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import academy.group5.dto.Posting;
import academy.group5.dto.PostingComment;
import academy.group5.dto.Recommend;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.dto.etc.Paging;
import academy.group5.dto.etc.SettingRecommend;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.BoardRepo;
import academy.group5.repo.MileageRepo;

@Service
@Transactional
public class PostingServiceImpl implements PostingService {

	/** 한 페이지에 표시되는 게시글의 수 */
	private final int POSTING_MAX_PAGE = 10;

	/** 댓글 삭제시 설정될 텍스트 */
	private final String DELETE_COMMENT_TEXT = "삭제된 댓글 입니다.";
	
	@Autowired
	BoardRepo boardRepo;
	
	@Autowired
	MileageRepo mileageRepo;
	
	@Override
	public List<Posting> postingList(int page, String postingType, String searchData, String searchType, String orderData) {
		return boardRepo.getAllPosting(new Paging(page, POSTING_MAX_PAGE, postingType, searchData, searchType, orderData));
	}
	
	@Override
	public Posting mostRecommend(MostRecommend searchData){
		return boardRepo.getMostRecommendPosting(searchData);
	}
	
	@Override
	public List<Posting> postingList(int page, String postingType){
		return boardRepo.getAllPosting(new Paging(page, POSTING_MAX_PAGE, postingType));
	}

	@Override
	public boolean postWrite(Posting posting) {
		String contentStr = posting.getPostingContent();
		contentStr = contentStr.replaceAll("\n", "<br>");
		posting.setPostingContent(contentStr);
		
		int result = boardRepo.setPosting(posting);
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean postModify(Posting posting) {
		int result = boardRepo.updateposting(posting);
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}
	
	@Override
	public boolean photoRegister(Posting posting) {
		int result = boardRepo.updatePhoto(posting);
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}
	
	private static final String IMG_PATH = "d:/academyImg/";
	private static final String PREVIEW_IMG_PATH = "d:/academyImg/preview_";
	private static final String TMP_PREFIX = "tmp_";
	
	/**
	 * 이미지 업로드
	 */
	@Override
	public int upload(MultipartFile uploadData, Posting postingData){
		// 원본 파일명
		String originalName = uploadData.getOriginalFilename();
		// 파일 확장자 추출
		String fileType = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
		// 게시글 번호
		Integer postingId = getPostingId(postingData);
		// 게시판 종류
		String postingType = postingData.getPostingType();

		// 파일명 : 게시판종류 + 게시글번호 + 확장자
		String fileName = postingType + "_" + postingId + "." + fileType;	
		
		String tmpFilePath = IMG_PATH + TMP_PREFIX + fileName;
		String filePath = IMG_PATH + fileName;
		String previewPath = PREVIEW_IMG_PATH + fileName;
		
		File originalFile = new File(tmpFilePath);
		// 파일 업로드
		try {
			uploadData.transferTo(originalFile);
			imageResize(tmpFilePath, filePath, fileType);
			previewImageResize(tmpFilePath, previewPath, fileType);
		} catch (IllegalStateException | IOException e) {
			return -1;
		} finally{
			// 임시 파일 삭제
			originalFile.delete();
		}
		
		postingData.setPostingPhoto(fileName);
		postingData.setPostingId(postingId);
		// DB에 저장된 파일의 이름을 등록
		if(!photoRegister(postingData)){
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * 업로드된 이미지 삭제
	 */
	@Override
	public void uploadCancel(Posting postingData, String defaultName){
		// 파일명
		String fileName = postingData.getPostingPhoto();		

		String filePath = IMG_PATH + fileName;
		String previewPath = PREVIEW_IMG_PATH + fileName;
		
		new File(filePath).delete();
		new File(previewPath).delete();
		
		// DB에 기본 이미지 설정
		if(defaultName != null){
			postingData.setPostingPhoto(defaultName);
			boardRepo.updatePhoto(postingData);
		}
	}
	
	// 이미지 최대 픽셀크기
	private static final int MAX_IMG_SIZE = 1280;
/** 이미지 저장 및 리사이징 */
	private void imageResize(String orgFilePath, String targetFilePath, String imageType) throws IOException{

		BufferedImage originalImage = ImageIO.read(new File(orgFilePath));
			
		int imgWidth = originalImage.getWidth();
		int imgHeight = originalImage.getHeight();
		
		double sizeRate;
		
		if(imgWidth > imgHeight && imgWidth > MAX_IMG_SIZE){
			sizeRate = imgWidth / imgHeight;
			
			imgWidth = MAX_IMG_SIZE;
			imgHeight = (int) (MAX_IMG_SIZE / sizeRate);
		} else if(imgWidth < imgHeight && imgHeight > MAX_IMG_SIZE){
			sizeRate = imgHeight / imgWidth;
			
			imgHeight = MAX_IMG_SIZE;
			imgWidth = (int) (MAX_IMG_SIZE / sizeRate);
		}

		// 이미지 크기 줄이기
		BufferedImage resizedImage = Scalr.resize(originalImage, imgWidth, imgHeight);
		
		// 이미지 저장
		ImageIO.write(resizedImage, imageType, new File(targetFilePath));
	}
	
	// 축소할 이미지의 픽셀크기
	private static final int TMP_IMG_SIZE = 150;	
	/** 미리보기 이미지 리사이징 및 저장 */
	private void previewImageResize(String orgFilePath, String targetFilePath, String imageType) throws IOException{

		BufferedImage originalImage = ImageIO.read(new File(orgFilePath));
			
		int imgWidth = originalImage.getWidth();
		int imgHeight = originalImage.getHeight();
		int minLength = Math.min(imgWidth, imgHeight);
		
		// 이미지 정사각형으로 자르기
		BufferedImage scaledImage = Scalr.crop(originalImage,
												(imgWidth - minLength)/2,
												(imgHeight - minLength)/2,
												minLength, minLength);		
		// 이미지 크기 줄이기
		BufferedImage resizedImage = Scalr.resize(scaledImage, TMP_IMG_SIZE, TMP_IMG_SIZE);
		// 이미지 저장
		ImageIO.write(resizedImage, imageType, new File(targetFilePath));
	}
	
	@Override
	public Integer getPostingId(Posting posting) {
		Integer result = boardRepo.getPostingId(posting);
		
		if(result == null){
			throw new WrongRequestException();
		}
		return result;
	}

	@Override
	public boolean postDelete(String userId, int postingId, String postingType) {
		Posting deleteData = postView(postingId, postingType);
		
		if(!deleteData.getUserId().equals(userId)){
			throw new WrongRequestException();
		}
			
		boardRepo.delAllComment(deleteData);
		boardRepo.delRecommend(deleteData);
		int result = boardRepo.delPosting(deleteData);
		
		if(result == 1){
			// 이미지 삭제
			if(!deleteData.getPostingPhoto().equals(DEFAULT_PHOTO_NAME)){
				uploadCancel(deleteData, null);
			}
			return true;
		} else {
			throw new WrongRequestException();
		}
	}

	@Override
	public Posting postView(Integer postingId, String postingType) {
		Posting postData = boardRepo.getPostingInfo(new Posting(postingId, postingType));
		
		if(postData == null){
			throw new WrongRequestException();
		}
		return postData;
	}

	@Override
	public Map<String, List<PostingComment>> commentList(Integer postingId, String postingType) {
		Map<String, List<PostingComment>> commentMap = new HashMap<>();
		
		List<PostingComment> parentList = boardRepo.getAllParentComment(new Posting(postingId, postingType));
		List<PostingComment> childList = boardRepo.getAllChildComment(new Posting(postingId, postingType));
		
		// 삭제된 부모 댓글인 경우 null 을 ''로 변경
		for(PostingComment parent : parentList){
			if(parent.getUserId() == null){
				parent.setUserId("");
			}
		}	
		commentMap.put("parent", parentList);
		commentMap.put("child", childList);
		
		return commentMap;
	}

	@Override
	public boolean commentWrite(PostingComment comment) {
		String contentStr = comment.getCommentContent();
		contentStr = contentStr.replaceAll("\n", "<br>");
		comment.setCommentContent(contentStr);
		
		Integer parentId = comment.getCommentParentId();
		if(parentId != null && boardRepo.isAliveComment(parentId) == 0){
			throw new WrongRequestException("이미 삭제된 댓글입니다.");		
		}
		int result = boardRepo.setComment(comment);
		
		if(result == 1){
			return true;
		} else {
			throw new WrongRequestException();
		}
	}

	@Override
	public boolean commentDelete(Integer commentId) {
		int childCount = boardRepo.isParentComment(commentId);
		int result;

		if(childCount > 0){
			result = boardRepo.delCommentSetDefault(new PostingComment(commentId, DELETE_COMMENT_TEXT));
		} else {
			result = boardRepo.delComment(commentId);
		}
		
		if(result == 1){
			return true;
		} else {
			throw new WrongRequestException();
		}
	}

	@Override
	public boolean commentModify(PostingComment comment) {
		int result = boardRepo.updateComment(comment);
		
		if(result == 1){
			return true;
		} else {
			throw new WrongRequestException();
		}
	}

	@Override
	public Integer getRecommendsCount(Integer postingId, String postingType){
		
		return boardRepo.getRecommendsCount(new Recommend(postingId, postingType));
	}
	
	@Override
	public boolean setRecommend(Integer postingId, String postingType, String userId) {
		Recommend recommendData = new Recommend(postingId, postingType, userId);

		int already = boardRepo.getRecommend(recommendData);
		// 이미 추천함
		if(already != 0){
			return false;
		}

		// 현재 추천수 + 1
		int recommendCount = getRecommendsCount(postingId, postingType) + 1;
		SettingRecommend recommendSettingData =
				new SettingRecommend(postingId, postingType, recommendCount);
		
		// 추천 적용
		if(boardRepo.setRecommendUser(recommendData) != 1 ||
				boardRepo.setRecommendPosting(recommendSettingData) != 1) {
			// 추천 실패
			throw new WrongRequestException();
		} 
		else {
			return true;
		}
	}
}
