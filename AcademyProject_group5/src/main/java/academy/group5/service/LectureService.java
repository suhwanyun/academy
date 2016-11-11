package academy.group5.service;

import java.util.Date;
import java.util.List;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.NextLectureTime;
import academy.group5.dto.etc.UserLectureTime;

public interface LectureService {
	/**
	 * 전체 강의목록 가져오기 page=10
	 * @return
	 */
	List<Lecture> allLectureList(int page, String searchData, String searchType);
	/**
	 * 강의 신청
	 * @param lectureId
	 * @param lectureClass
	 * @param userId
	 * @return
	 */
	boolean apply(Integer lectureId, String userId, Integer lectureClass, String isPresidentStr);
	/**
	 * 신청이 되어있는 강의인지 확인
	 * @param lectureId
	 * @param userId
	 * @param lectureClass
	 * @return
	 */
	boolean isAppliedLecture(Integer lectureId, String userId, Integer lectureClass);
	/**
	 * 강의 신청 취소
	 * @param lectureId
	 * @param lectureClass
	 * @param userId
	 * @return
	 */
	boolean cancelApply(Integer lectureId, String userId);
	/**
	 * 회원의 강의목록 가져오기
	 * @param userId
	 * @return
	 */
	List<UserLectureTime> userLectureList(String userId);
	
	/**
	 * 강의 정보(분반 별)
	 * @param lectureId
	 * @param lectureClass
	 * @return
	 */
	Lecture lectureClassInfo(Integer lectureId, Integer lectureClass);
	
	/**
	 * 강의 시간 정보
	 * @param lecture
	 * @return
	 */
	List<LectureTime> lectureTimeInfo(Lecture lecture);
	
	/**
	 * 시간표
	 * @return
	 */
	List<UserLectureTime> timetable(String userId);
	/**
	 * 해당 강의의 반장여부를 반환
	 * @param lectureId
	 * @param userId
	 * @param lectureClass
	 * @return
	 */
	boolean getIsPresident(Integer lectureId, String userId, Integer lectureClass);
	/**
	 * 강의 공지 작성
	 * @param lecturenotice
	 * @return
	 */
	boolean postNotice(LectureNotice lecturenotice, Date noticeDay, Date noticeTime);
	/**
	 * 다음 강의 날짜 확인
	 * @param lectureId
	 * @param lectureClas
	 * @return
	 */
	List<NextLectureTime> getNextLectureTime(Integer lectureId, Integer lectureClass);
}
