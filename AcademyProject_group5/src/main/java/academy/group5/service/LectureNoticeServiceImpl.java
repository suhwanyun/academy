package academy.group5.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.LectureNotice;
import academy.group5.dto.etc.LecturePaging;
import academy.group5.dto.etc.UserLectureNotice;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.LectureNoticeRepo;
import academy.group5.util.GCM;

@Service
@Transactional
public class LectureNoticeServiceImpl implements LectureNoticeService{

	/** 한 페이지에 표시되는 공지의 수 */
	private final int NOTICE_MAX_PAGE = 5;
	
	@Autowired
	LectureNoticeRepo notiRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	@Override
	public List<UserLectureNotice> allLectureNoticeList(String userId, int page) {
		return notiRepo.getAllLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, userId));
	}

	@Override
	public List<LectureNotice> lectureNoticeList(Integer lectureId, Integer lectureClass, int page) {
		return notiRepo.getLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, lectureId, lectureClass));
	}

	@Override
	public LectureNotice lectureNoticeInfo(String noticeTime, Integer lectureId, Integer lectureClass) {
		LectureNotice noticeData = notiRepo.getLectureNoticeInfo(new LectureNotice(noticeTime, lectureId, lectureClass));
		
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
