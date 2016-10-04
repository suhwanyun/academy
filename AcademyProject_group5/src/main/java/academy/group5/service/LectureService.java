package academy.group5.service;

import java.util.List;

import academy.group5.dto.Lecture;
import academy.group5.dto.Lecturetime;

public interface LectureService {
	/**
	 * 전체 강의목록 가져오기
	 * @return
	 */
	List<Lecture> allLectureList();
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
	List<Lecture> myLectureList(String userId);
	/**
	 * 시간표
	 * @param lectureId
	 * @param lectureClass
	 * @return
	 */
	List<Lecturetime> timetable(Integer lectureId, Integer lectureClass, String userId);
	char getIsPresident(Integer lectureId, String userId, Integer lectureClass);
	//boolean postNotice()

}
