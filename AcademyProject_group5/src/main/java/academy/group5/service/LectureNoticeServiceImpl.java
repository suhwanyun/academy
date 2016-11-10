package academy.group5.service;

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
		List<UserLectureNotice> noticeList = notiRepo.getAllLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, userId));
		
		for(UserLectureNotice noticeData : noticeList){
			String timeStr = noticeData.getNoticeTime();
			noticeData.setNoticeTime(timeStr.replaceAll(" ", "<br>"));
		}
		
		return noticeList;
	}

	@Override
	public List<LectureNotice> lectureNoticeList(Integer lectureId, Integer lectureClass, int page) {
	
		return notiRepo.getLectureNoticeList(new LecturePaging(page, NOTICE_MAX_PAGE, lectureId, lectureClass));
	}

	@Override
	public LectureNotice lectureNoticeInfo(Integer lectureNoticeId) {
		LectureNotice noticeData = notiRepo.getLectureNoticeInfo(lectureNoticeId);
		
		if(noticeData == null){
			throw new WrongRequestException();
		}
		return noticeData;
	}

	@Override
	public boolean postNotice(LectureNotice lectureNotice) {
		int result = notiRepo.setLectureNotice(lectureNotice);
		if(result != 1){
			throw new WrongRequestException();
		} else {
			
			
			// 메세지 PUSH
			new GCM(lectureNotice.getNoticeTitle(), 
					lectureNotice.getNoticeContent(),
					gcmRepo.getAllUser(),
					GCM.TYPE_NOTICE);
		}
		return true;
	}
}
