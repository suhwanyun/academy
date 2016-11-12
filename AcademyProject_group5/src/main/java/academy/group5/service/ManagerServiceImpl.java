package academy.group5.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.Manager;
import academy.group5.dto.Mileage;
import academy.group5.dto.MileageProduct;
import academy.group5.dto.Term;
import academy.group5.dto.etc.Paging;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.ManagerRepo;
import academy.group5.repo.TermRepo;
import academy.group5.util.MyHash;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	TermRepo termRepo;
	
	@Autowired
	ManagerRepo managerRepo;
	
	/** 한 페이지에 표시되는 강의의 수 */
	private final int LECTURE_MAX_PAGE = 10;
	/** 한 페이지에 표시되는 마일리지 상품의 수 */
	private final int MILEAGE_PRODUCT_MAX_PAGE = 10;
	/** 한 페이지에 표시되는 마일리지의 수 */
	private final int MILEAGE_MAX_PAGE = 10;
	
	@Override
	public boolean isTermSetted(){
		if(termRepo.getNextTermStartDate() != null
				&& termRepo.getTodayTerm() != null){
			return true;
		}
		throw new WrongRequestException("강의 등록을 위해, 다음 학기를 등록해주세요");
		
	}
	
	@Override
	public boolean isNotTermSetted(){
		if(termRepo.getNextTermStartDate() != null
				&& termRepo.getTodayTerm() != null){
			throw new WrongRequestException("이미 학기가 등록되어있습니다.");
		}
		return true;
	}
	
	@Override
	public String managerLogin(String managerId, String managerPass) {
		
		String managerType = null;
		Manager loginInfo = toHash(new Manager(managerId, managerPass, null));
		
		managerType = managerRepo.getManager(loginInfo);
		if(managerType == null){
			throw new WrongRequestException("아이디 또는 비밀번호를 확인하세요.");
		}
		
		return managerType;
	}

	@Override
	public boolean registTerm(Date termStart, Date termEnd) {
		Calendar cal = Calendar.getInstance();
		
		if(termStart == null || termEnd == null){
			throw new WrongRequestException();
		} else if(termStart.before(cal.getTime()) || termEnd.before(cal.getTime())){
			throw new WrongRequestException("현재 시간보다 이후로 설정해주세요");
		} else if(termEnd.before(termStart)){
			throw new WrongRequestException("학기 종료시간이 시작시간보다 앞설 수 없습니다.");
		}
		
		Term term = new Term(termStart, termEnd);
		
		if(termRepo.getTermByTerm(term) == null && termRepo.getTermByDate(term).size() == 0){		
			termRepo.setTerm(term);
			return true;
		} 
			
		throw new WrongRequestException("이미 등록된 학기정보와 중복됩니다.");	
	}

	@Override
	public boolean registLecture(Integer lectureId, String lectureName, String professorName, Integer lectureClass) {
		
		Lecture lectureData = new Lecture(lectureId, lectureClass, lectureName, professorName);
		
		Lecture isAlready = managerRepo.getLecture(lectureData);
		if(isAlready != null){
			throw new WrongRequestException("이미 등록된 강의입니다.");
		}
		
		int result = managerRepo.setLecture(lectureData);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean registLectureTime(LectureTime lectureTime) {
		// 중복 확인
		List<LectureTime> alreadyRegistList = managerRepo.getAlreadyLectureTime(lectureTime);
		isAlreadyRectureTimeErrorOccurred(alreadyRegistList);
		
		int result = managerRepo.insertLectureTime(lectureTime);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}
	
	@Override
	public List<Lecture> getAllLectureList(){
		List<Lecture> lectureList = managerRepo.getAllLecture(new Paging(1, LECTURE_MAX_PAGE));
		
		for(Lecture lectureData : lectureList){
			List<LectureTime> timeData = managerRepo.getAllLectureTime(lectureData);
			lectureData.setLectureTimeList(timeData);
		}
		return lectureList;
	}
	
	@Override
	public List<Lecture> getAllLectureListBySearch(int page, String searchData, String searchType){
		List<Lecture> lectureList = managerRepo.getAllLecture(new Paging(page, LECTURE_MAX_PAGE, searchData, searchType));
		
		for(Lecture lectureData : lectureList){
			List<LectureTime> timeData = managerRepo.getAllLectureTime(lectureData);
			lectureData.setLectureTimeList(timeData);
		}
		return lectureList;
	}
	
	@Override
	public int getMaxLectureListPage(){
		int result = managerRepo.getLectureListCount(new Paging());
		result /= LECTURE_MAX_PAGE;
		return ++result;
	}
	
	@Override
	public int getMaxLectureListPageBySearch(String searchData, String searchType){
		int result = managerRepo.getLectureListCount(new Paging(searchData, searchType));
		result /= LECTURE_MAX_PAGE;
		return ++result;
	}
	
	@Override
	public Lecture getLecture(int lectureId, int lectureClass){
		Lecture lectureData = managerRepo.getLecture(new Lecture(lectureId, lectureClass));
		if(lectureData == null){
			throw new WrongRequestException();
		}
		
		return lectureData;
	}
	
	@Override
	public LectureTime getLectureTime(int lectureTImeId){
		LectureTime lectureTimeData = managerRepo.getLectureTime(lectureTImeId);
		if(lectureTimeData == null){
			throw new WrongRequestException();
		}
		
		return lectureTimeData;
	}

	@Override
	public boolean updateLecture(Integer lectureId, Integer lectureClass, String lectureName, String professorName){
		int result = managerRepo.updateLecture(new Lecture(lectureId, lectureClass, lectureName, professorName));
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean updateLectureTime(LectureTime lectureTime) {
		// 중복 확인
		List<LectureTime> alreadyRegistList = managerRepo.getAlreadyLectureTime(lectureTime);
		isAlreadyRectureTimeErrorOccurred(alreadyRegistList);
		
		int result = managerRepo.updateLectureTime(lectureTime);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean deleteLecture(Integer lectureId, Integer lectureClass) {
		Lecture lectureData = new Lecture(lectureId, lectureClass);
		// 강의 시간 삭제
		deleteAllLectureTime(lectureData);
		// 강의 삭제
		int result = managerRepo.deleteLecture(lectureData);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public void deleteAllLectureTime(Lecture lectureData) {
		managerRepo.deleteAllLectureTime(lectureData);
	}
	
	@Override
	public boolean deleteLectureTime(Integer lectureTimeId){
		int result = managerRepo.deleteLectureTime(lectureTimeId);
		if(result != 1){
			throw new WrongRequestException();
		}
		return false;
	}
	
	private String[] weekList = {"일", "월", "화", "수", "목", "금", "토"};
	/** 강의 시간 중복 여부 확인 로직 */
	private void isAlreadyRectureTimeErrorOccurred(List<LectureTime> alreadyRegistList){
		if(alreadyRegistList.size() == 0){
			return;
		}
		String errorStr = "강의시간이 중복됩니다.";
		for(LectureTime alreadyData : alreadyRegistList){
			errorStr += "\\n" + alreadyData.getLectureClass() + "분반(";
			errorStr += weekList[alreadyData.getLectureWeek()-1] + "요일 ";
			errorStr += alreadyData.getLectureStart() + "교시~";
			errorStr += alreadyData.getLectureEnd() + "교시)";
		}
	
		throw new WrongRequestException(errorStr);
	}
	
	// ----------------------------마일리지---------------------------- */

	public List<Mileage> getAllMileage(int page, String orderType, boolean isAsc) {
		return managerRepo.getAllMileage(new Paging(page, MILEAGE_MAX_PAGE, null, null, null, orderType, isAsc));
	}
	
	@Override
	public boolean registMileage(String mileName, int mileValue) {
		if(managerRepo.getMileage(mileName) != null){
			throw new WrongRequestException("이미 등록된 이름입니다.");
		}
		int result = managerRepo.setMileage(new Mileage(mileName, mileValue));
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean deleteMileage(String mileName) {
		int result = managerRepo.delMileage(mileName);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}
	
	@Override
	public List<MileageProduct> getAllProduct(int page, String orderType, boolean isAsc) {
		return managerRepo.getAllMileageProduct(new Paging(page, MILEAGE_PRODUCT_MAX_PAGE, null, null, null, orderType, isAsc));
	}
	
	@Override
	public MileageProduct getProduct(int productId) {
		MileageProduct productData = managerRepo.getMileageProduct(productId);
		if(productData == null) {
			throw new WrongRequestException();
		}
		return productData;
	}
	
	@Override
	public boolean registerProduct(String productName, int productCost, String productContent, String productImgfile) {
		productContent = productContent.replaceAll("\n", "<br>");
		int result = managerRepo.setMileageProduct(
				new MileageProduct(productName, productCost, productContent, 
						productImgfile == null ? PostingService.DEFAULT_PHOTO_NAME : productImgfile));
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean updateProduct(int productId, String productName, int productCost, String productContent, String productImgfile) {
		productContent = productContent.replaceAll("\n", "<br>");
		int result = managerRepo.updateMileageProduct(
				new MileageProduct(productName, productCost, productContent, productImgfile));
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean deleteProduct(Integer productId) {
		int result = managerRepo.delMileageProduct(productId);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}
	
	// ----------------------------기타 로직---------------------------- */

	/** 암호화 */
	private Manager toHash(Manager data){
		String oriPass = data.getManagerPass();

		if(oriPass != null && !oriPass.equals("")){
			String newPass = MyHash.MD5(oriPass);
			data.setManagerPass(newPass);
		}
		
		return data;
	}
}
