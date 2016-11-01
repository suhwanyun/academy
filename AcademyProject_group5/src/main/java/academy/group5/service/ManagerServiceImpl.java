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
	public boolean registerLecture(String lectureName, String professorName, Integer lectureClass) {
		
		Lecture lectureData = new Lecture(null, lectureClass, lectureName, professorName);
		
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
	public boolean registerLecturetime(LectureTime lecturetime) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Lecture> getAllLectureList(int page){
		return managerRepo.getAllLecture(new Paging(page, LECTURE_MAX_PAGE));
	}

	@Override
	public boolean updateLecture(Lecture lecture) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLecturetime(LectureTime lecturetime) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteLecture(Integer lectureId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteLecturetime(Integer lectureId, Integer lectureStart) {
		// TODO Auto-generated method stub
		return false;
	}

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
