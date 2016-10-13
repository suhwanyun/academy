package academy.group5.service;

import java.util.List;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;

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
	boolean apply(Integer lectureId,Integer lectureClass, String userId);
	/**
	 * 강의 삭제
	 * @param lectureId
	 * @param lectureClass
	 * @param userId
	 * @return
	 */
	boolean delete(Integer lectureId, String userId);
	/**
	 * 회원의 강의목록 가져오기
	 * @param userId
	 * @return
	 */
	List<Lecture> userLectureList(String userId);
	
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
	List<LectureTime> timetable(String userId);
	/**
	 * 해당 강의의 반장여부를 반환
	 * @param lectureId
	 * @param userId
	 * @param lectureClass
	 * @return
	 */
	boolean getIsPresident(Integer lectureId, String userId, Integer lectureClass);
	

}
