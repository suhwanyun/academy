package academy.group5.service;

import java.util.List;

import academy.group5.dto.LectureNotice;

public interface NoticeService {
	/**
	 * 전체 강의의 공지 가져오기 page=5
	 * @param userId
	 * @param page
	 * @return
	 */
	List<LectureNotice> allLecturenoticeList(String userId, int page);
	/**
	 * 특정 강의의 전체 공지 가져오기 page=5
	 * @param lectureId
	 * @param lectureClass
	 * @return
	 */
	List<LectureNotice> lecturenoticeList(Integer lectureId, Integer lectureClass, int page);
	/**
	 * 강의공지의 상세 정보
	 * @param lectureId
	 * @return
	 */
	LectureNotice lectureInfo(Integer lectureId);
	/**
	 * 강의 공지 작성
	 * @param lecturenotice
	 * @return
	 */
	boolean postNotice(LectureNotice lecturenotice);
	
}
