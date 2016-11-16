package academy.group5.service;

import java.util.Date;
import java.util.List;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureApply;
import academy.group5.dto.LectureNotice;
import academy.group5.dto.LectureTime;
import academy.group5.dto.etc.LectureNoticeSetTime;
import academy.group5.dto.etc.UserLectureTime;

public interface LectureService {
	/** 한 페이지에 표시되는 강의의 수 */
	final int LECTURE_MAX_PAGE = 10;
	
	// 강의 시간 계산 기준
	// 1교시 = 9시 = 8 + 1 = 9
	final int FIRST_CLASS_CRITERIA = 8; 
	
	final String[] weekList = {"일", "월", "화", "수", "목", "금", "토"};
	
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
	 * 학기가 시작되었는지 확인
	 * @return
	 */
	public boolean isTermStarted();
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
	 * 해당 강의의 반장여부를 반환(강의정보 전달)
	 * @return
	 */
	boolean getIsPresident(LectureApply applyData);
	/**
	 * 해당 강의의 반장여부를 반환
	 * @param lectureTimeId
	 * @param userId
	 * @return
	 */
	boolean getIsPresident(Integer lectureTimeId, String userId);
	/**
	 * 다음 강의 날짜 확인
	 * @param lectureId
	 * @param lectureClas
	 * @return
	 */
	List<LectureTime> getNextLectureTime(Integer lectureId, Integer lectureClass);
	/**
	 *  강의 시간을 텍스트로 변환
	 * @param timeData
	 * @return
	 */
	String getLectureTimeStr(LectureTime timeData);
	/**
	 * ID로 강의시간 정보 확인
	 * @param lectureTimeId
	 * @return
	 */
	LectureTime getLectureTimeById(int lectureTimeId);
	/**
	 * 돌아오는 다음 강의 날짜 계산
	 * @param lectureWeek
	 * @param startTime
	 * @return
	 */
	Date getNextLectureDate(int lectureWeek, int startTime);
	/**
	 * 강의 이름 확인
	 * @param lectureId
	 * @param lectureClass
	 * @return
	 */
	public String getLectureName(int lectureId, int lectureClass);
}
