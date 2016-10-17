package academy.group5.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import academy.group5.dto.Posting;
import academy.group5.dto.PostingComment;
import academy.group5.dto.etc.Paging;
import academy.group5.repo.BoardRepo;

@Service
@Transactional
public class PostingServiceImpl implements PostingService {

	/** 한 페이지에 표시되는 게시글의 수 */
	private final int POSTING_MAX_PAGE = 10;
	
	@Autowired
	BoardRepo boardRepo;
	
	@Override
	public List<Posting> postingList(int page, String postingType, String searchData, String searchType) {
		return boardRepo.getAllPosting(new Paging(page, POSTING_MAX_PAGE, postingType, searchData, searchType));
	}
	
	@Override
	public List<Posting> postingList(int page, String postingType) {
		return boardRepo.getAllPosting(new Paging(page, POSTING_MAX_PAGE, postingType));
	}

	@Override
	public boolean postWrite(Posting posting) {
		int result = boardRepo.setPosting(posting);
		
		if(result != 1){
			return false;
		}
		return true;
	}

	@Override
	public boolean postModify(Posting posting) {
		int result = boardRepo.updateposting(posting);
		
		if(result != 1){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean photoRegister(Posting posting) {
		int result = boardRepo.updatePhoto(posting);
		
		if(result != 1){
			return false;
		}
		return true;
	}
	
	private static final String IMG_PATH = "d:/academyImg/";
	
	@Override
	public int upload(MultipartFile uploadData, Posting postingData){
		// 원본 파일명
		String originalName = uploadData.getOriginalFilename();
		// 파일 확장자 추출
		String fileTypeStr = originalName.substring(originalName.lastIndexOf("."), originalName.length());
		// 게시글 번호
		Integer postingId = getPostingId(postingData);
		// 게시판 종류
		String postingType = postingData.getPostingType();

		// 비정상적 접근
		if(postingId == null){
			return -1;
		}
		// 파일명 : 게시판종류 + 게시글번호 + 확장자
		String fileName = postingType + "_" + postingId + fileTypeStr;			
		File file = new File(IMG_PATH + fileName);
		
		// 파일 업로드
		try {
			uploadData.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			return -1;
		}
		
		postingData.setPostingPhoto(fileName);
		postingData.setPostingId(postingId);
		// DB에 저장된 파일의 이름을 등록
		if(!photoRegister(postingData)){
			return -1;
		}
		
		return 0;
	}
	
	@Override
	public Integer getPostingId(Posting posting) {
		return boardRepo.getPostingId(posting);
	}

	@Override
	public boolean postDelete(Integer postingId, String postingType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Posting postView(Integer postingId, String postingType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostingComment> commentList(Integer postingId, String postingType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean commentWrite(PostingComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commentDelete(Integer commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commentModify(PostingComment comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recommend(Integer postingId, String postingType, String userId) {
		// TODO Auto-generated method stub
		return false;
	}
}
