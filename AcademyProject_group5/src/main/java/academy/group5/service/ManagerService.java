package academy.group5.service;

import academy.group5.dto.Lecture;
import academy.group5.dto.Lecturetime;
import academy.group5.dto.Manager;
import academy.group5.dto.Mileageproduct;
import academy.group5.dto.Term;

public interface ManagerService {
	/**
	 * 로그인
	 * @param managerId
	 * @param managerPass
	 * @return
	 */
	Manager managerLogin(String managerId, String managerPass);
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
	boolean registerLecture(Lecture lecture);
	/**
	 * 강의 시간 등록
	 * @param lecturetime
	 * @return
	 */
	boolean registerLecturetime(Lecturetime lecturetime);
	/**
	 * 강의 내용 수정
	 * @param lecture
	 * @return
	 */
	boolean updateLecture(Lecture lecture);
	/**
	 * 강의 시간 내용 수정
	 * @param lecturetime
	 * @return
	 */
	boolean updateLecturetime(Lecturetime lecturetime);
	/**
	 * 강의삭제(강의에 시간까지 같이 삭제)
	 * @param lectureId
	 * @return
	 */
	boolean deleteLecture(Integer lectureId);
	/**
	 * 강의 시간삭제
	 * @param lectureId
	 * @param lectureStart
	 * @return
	 */
	boolean deleteLecturetime(Integer lectureId, Integer lectureStart);
	/**
	 * 상품 등록
	 * @param mileageProduct
	 * @return
	 */
	boolean registerProduct(Mileageproduct mileageProduct);
	/**
	 * 상품 수정
	 * @param mileageProduct
	 * @return
	 */
	boolean updateProduct(Mileageproduct mileageProduct);
	/**
	 * 상품 삭제
	 * @param productId
	 * @return
	 */
	boolean deleteProduct(Integer productId);
	
}
