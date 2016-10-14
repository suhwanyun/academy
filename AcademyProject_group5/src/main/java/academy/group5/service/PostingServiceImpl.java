package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Posting> postingList(String lec_lecId_lecNum, int page) {
		
		return boardRepo.getAllPosting(new Paging(page, POSTING_MAX_PAGE, lec_lecId_lecNum));
	}

	@Override
	public boolean postWrite(Posting data) {
		
		int result = boardRepo.posting(data);
		
		if(result != 1){
			return false;
		}
		return true;
	}

	@Override
	public boolean postModify(Posting posting) {
		// TODO Auto-generated method stub
		return false;
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
