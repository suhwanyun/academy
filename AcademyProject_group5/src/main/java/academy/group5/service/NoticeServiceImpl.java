package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.LectureNotice;
import academy.group5.dto.etc.LecturePaging;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.NoticeRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

	/** 한 페이지에 표시되는 공지의 수 */
	private final int NOTICE_MAX_PAGE = 5;
	
	@Autowired
	NoticeRepo notiRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
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
		LectureNotice noticeData = notiRepo.getLectureNoticeInfo(lecturenotice);
		
		if(noticeData == null){
			throw new WrongRequestException();
		}
		return noticeData;
	}

	@Override
	public boolean postNotice(LectureNotice lecturenotice) {
		int result = notiRepo.setLectureNotice(lecturenotice);
		if(result != 1){
			throw new WrongRequestException();
		} else {
			// 메세지 PUSH
			new GCM(lecturenotice.getNoticeTitle(), 
					lecturenotice.getNoticeContent(),
					gcmRepo.getAllUser(),
					lecturenotice.getNoticeType());
		}
		return true;
	}
}
