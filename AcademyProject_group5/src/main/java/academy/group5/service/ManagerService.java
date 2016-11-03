package academy.group5.service;

import java.util.List;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.MileageProduct;
import academy.group5.dto.Term;

public interface ManagerService {
	
	public final String TYPE_LECTURE = "lecture";
	public final String TYPE_MILEAGE = "mileage";
	/**
	 * 로그인
	 * @param managerId
	 * @param managerPass
	 * @return
	 */
	String managerLogin(String managerId, String managerPass);
	/**
	 * 학기등록
	 * @param term
	 * @return
	 */
	boolean registerTerm(Term term);
	
	/**
	 * 강의등록
	 * @param lecture
	 * @return
	 */
	boolean registerLecture(Integer lectureId, String lectureName, String professorName, Integer lectureClass);
	/**
	 * 강의 시간 등록
	 * @param LectureTime
	 * @return
	 */
	boolean registerLectureTime(LectureTime lecturetime);
	
	/**
	 * 강의 리스트 불러오기
	 * @return
	 */
	List<Lecture> getAllLectureList(int page); 
	
	/**
	 * 강의 정보 불러오기
	 * @param lectureId
	 * @param lectureClass
	 * @return
	 */
	Lecture getLecture(int lectureId, int lectureClass);
	
	/**
	 * 강의 시간 불러오기(하나)
	 * @param lectureTImeId
	 * @return
	 */
	public LectureTime getLectureTime(int lectureTImeId);
	
	/**
	 * 강의 내용 수정
	 * @param lecture
	 * @return
	 */
	boolean updateLecture(Integer lectureId, Integer lectureClass, String lectureName, String professorName);
	/**
	 * 강의 시간 내용 수정
	 * @param lecturetime
	 * @return
	 */
	boolean updateLectureTime(LectureTime lecturetime);
	/**
	 * 강의삭제(강의에 시간까지 같이 삭제)
	 * @param lectureId
	 * @return
	 */
	boolean deleteLecture(Integer lectureId, Integer lectureClass);
	/**
	 * 강의 시간삭제
	 * @return
	 */
	void deleteAllLectureTime(Lecture lectureData);
	boolean deleteLectureTime(Integer lectureTimeId);
	/**
	 * 상품 등록
	 * @param mileageProduct
	 * @return
	 */
	boolean registerProduct(MileageProduct mileageProduct);
	/**
	 * 상품 수정
	 * @param mileageProduct
	 * @return
	 */
	boolean updateProduct(MileageProduct mileageProduct);
	/**
	 * 상품 삭제
	 * @param productId
	 * @return
	 */
	boolean deleteProduct(Integer productId);
	
}
