package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.LectureNotice;
import academy.group5.dto.etc.LecturePaging;
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
	public List<LectureNotice> allLectureNoticeList(String userId, int page) {
		return notiRepo.getAllLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, userId));
	}

	@Override
	public List<LectureNotice> lectureNoticeList(Integer lectureId, Integer lectureClass, int page) {
		return notiRepo.getLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, lectureId, lectureClass));
	}

	@Override
	public LectureNotice lectureNoticeInfo(LectureNotice lecturenotice) {
		return notiRepo.getLectureNoticeInfo(lecturenotice);
	}

	@Override
	public boolean postNotice(LectureNotice lecturenotice) {
		int result = notiRepo.setLectureNotice(lecturenotice);
		if(result == 1){
			return true;
		} else{
			return false;
		}
	}

}
