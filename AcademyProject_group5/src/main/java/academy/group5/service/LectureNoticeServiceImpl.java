package academy.group5.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
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

	@SuppressWarnings("deprecation")
	@Override
	public boolean postNotice(LectureNotice lectureNotice, Date noticeDay, Date noticeTime) {
		int result = notiRepo.setLectureNotice(lectureNotice);
		if(result != 1){
			throw new WrongRequestException();
		} 
		Integer lectureId = lectureNotice.getLectureId();
		Integer lectureClass = lectureNotice.getLectureClass();
		List<String> userIdList = gcmRepo.getLectureApplyUser(new Lecture(lectureId, lectureClass));
		Calendar noticeDate = null;
		// 특정 시간에 띄울 알림인 경우
		if(noticeDay != null && noticeTime != null) {
			
			noticeDate = Calendar.getInstance();
			noticeDate.setTime(noticeDay);
			noticeDate.set(Calendar.HOUR_OF_DAY, noticeTime.getHours());
			noticeDate.set(Calendar.MINUTE, noticeTime.getMinutes());
			noticeDate.set(Calendar.SECOND, noticeTime.getSeconds());
			
			// 시간이 과거로 설정된 경우
			Calendar today = Calendar.getInstance();
			if(noticeDate.before(today)){
				throw new WrongRequestException("알림 시간이 잘못 설정되었습니다.");
			}
		}
		// 메세지 PUSH
		new GCM(lectureNotice.getNoticeTitle(), 
				lectureNotice.getNoticeContent(),
				userIdList,
				GCM.TYPE_NOTICE,
				noticeDate);
		
		return true;
	}
}
