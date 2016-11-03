package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.Manager;
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
	public boolean registerTerm(Term term) {
		if(termRepo.getTermByTerm(term) == null && termRepo.getTermByDate(term).size() == 0){		
			termRepo.setTerm(term);
			return true;
		} 
			
		return false;	
	}

	@Override
	public boolean registerLecture(Integer lectureId, String lectureName, String professorName, Integer lectureClass) {
		
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
	public boolean registerLectureTime(LectureTime lectureTime) {
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
	public List<Lecture> getAllLectureList(int page){
		List<Lecture> lectureList = managerRepo.getAllLecture(new Paging(page, LECTURE_MAX_PAGE));
		
		for(Lecture lectureData : lectureList){
			List<LectureTime> timeData = managerRepo.getAllLectureTime(lectureData);
			lectureData.setLectureTimeList(timeData);
		}
		return lectureList;
	}
	
	@Override
	public int getMaxLectureListPage(){
		int result = managerRepo.getAllLectureCount();
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
	
	/** 강의 시간 중복 여부 확인 로직 */
	private void isAlreadyRectureTimeErrorOccurred(List<LectureTime> alreadyRegistList){
		if(alreadyRegistList.size() == 0){
			return;
		}
		String outputStr = "강의시간이 중복됩니다.";
		for(LectureTime alreadyData : alreadyRegistList){
			outputStr += "\n" + alreadyData.getLectureClass() + "분반(";
			outputStr += alreadyData.getLectureStart() + "교시~";
			outputStr += alreadyData.getLectureEnd() + "교시)";
		}
		
		throw new WrongRequestException(outputStr);
	}
	
	/** ----------------------------마일리지---------------------------- */

	@Override
	public boolean registerProduct(MileageProduct mileageProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(MileageProduct mileageProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/** ----------------------------기타 로직---------------------------- */

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
