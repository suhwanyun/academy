package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.LectureNotice;
import academy.group5.dto.etc.Paging;
import academy.group5.repo.NoticeRepo;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

	/** 한 페이지에 표시되는 공지의 수 */
	private final int NOTICE_MAX_PAGE = 5;
	
	@Autowired
	NoticeRepo notiRepo;
	
	@Override
	public List<LectureNotice> allLecturenoticeList(String userId, int page) {
		return notiRepo.getAllLectureNoticeList(new Paging(page, NOTICE_MAX_PAGE, userId));
	}

	@Override
	public List<LectureNotice> lecturenoticeList(Integer lectureId, Integer lectureClass, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LectureNotice lectureInfo(Integer lectureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postNotice(LectureNotice lecturenotice) {
		// TODO Auto-generated method stub
		return false;
	}

}
