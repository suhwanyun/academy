package academy.group5.service;

import java.util.List;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.Mileage;
import academy.group5.dto.MileageProduct;

public interface ManagerService {
	
	public final String TYPE_LECTURE = "lecture";
	public final String TYPE_MILEAGE = "mileage";
	
	/**
	 * 학기 설정 여부 확인
	 * @return
	 */
	boolean isTermSetted();
	/**
	 * 학기 설정이 되어있을 때 에러발생
	 * @return
	 */
	boolean isNotTermSetted();
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
	void registTerm(String termStart, String termEnd);
	
	/**
	 * 강의등록
	 * @param lecture
	 * @return
	 */
	boolean registLecture(Integer lectureId, String lectureName, String professorName, Integer lectureClass);
	/**
	 * 강의 시간 등록
	 * @param UserLectureTime
	 * @return
	 */
	boolean registLectureTime(LectureTime lecturetime);
	
	/**
	 * 전체 강의 리스트 불러오기
	 * @return
	 */
	List<Lecture> getAllLectureList(); 
	/**
	 * 검색된 강의 리스트 불러오기
	 * @param page
	 * @param searchType
	 * @param searchData
	 * @return
	 */
	List<Lecture> getAllLectureListBySearch(int page, String searchType, String searchData);
	
	/**
	 * 전체 강의 페이지 수 확인
	 * @return
	 */
	int getMaxLectureListPage();
	/**
	 * 검색된 강의 페이지 수 확인
	 * @param searchType
	 * @param searchData
	 * @return
	 */
	int getMaxLectureListPageBySearch(String searchType, String searchData);
	
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
	boolean deleteLectureTime(Integer lectureTimeId);
	/**
	 * 마일리지 물품 리스트 확인
	 * @param page
	 * @return
	 */
	List<MileageProduct> getAllProduct(int page, String orderType, boolean isAsc);
	/**
	 * 마일리지 물품 정보 확인
	 * @param productId
	 * @return
	 */
	MileageProduct getProduct(int productId);
	/**
	 * 상품 등록
	 * @param mileageProduct
	 * @return
	 */
	boolean registerProduct(String productName, int productCost, String productContent, String productImgfile);
	/**
	 * 상품 수정
	 * @param mileageProduct
	 * @return
	 */
	boolean updateProduct(int productId, String productName, int productCost, String productContent, String productImgfile);
	/**
	 * 상품 삭제
	 * @param productId
	 * @return
	 */
	boolean deleteProduct(Integer productId);
	
}
