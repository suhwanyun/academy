package academy.group5.service;

import java.util.Date;
import java.util.List;

import academy.group5.dto.LectureNotice;
import academy.group5.dto.etc.UserLectureNotice;

public interface LectureNoticeService {
	/**
	 * 전체 강의의 공지 가져오기 page=5
	 * @param userId
	 * @param page
	 * @return
	 */
	List<UserLectureNotice> allLectureNoticeList(String userId, int page);
	/**
	 * 특정 강의의 전체 공지 가져오기 page=5
	 * @param lectureId
	 * @param lectureClass
	 * @return
	 */
	List<LectureNotice> lectureNoticeList(Integer lectureId, Integer lectureClass, int page);
	/**
	 * 강의공지의 상세 정보
	 * @param notice
	 * @return
	 */
	LectureNotice lectureNoticeInfo(Integer lectureNoticeId);
	/**
	 * 강의 공지 작성
	 * @param lecturenotice
	 * @return
	 */
	boolean postNotice(LectureNotice lecturenotice);
	
}
