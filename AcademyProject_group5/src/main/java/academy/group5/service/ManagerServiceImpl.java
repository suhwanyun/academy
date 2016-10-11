package academy.group5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.Manager;
import academy.group5.dto.MileageProduct;
import academy.group5.dto.Term;
import academy.group5.repo.TermRepo;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	TermRepo termRepo;
	
	@Override
	public Manager managerLogin(String managerId, String managerPass) {
		// TODO Auto-generated method stub
		return null;
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
	public boolean registerLecture(Lecture lecture) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerLecturetime(LectureTime lecturetime) {
		// TODO Auto-generated method stub
		return false;
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

}
