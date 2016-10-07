package academy.group5.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.LectureNotice;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

	@Override
	public List<LectureNotice> allLecturenoticeList(String userId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LectureNotice> lecturenoticeList(Integer lectureId, Integer lectureClass, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LectureNotice lectureInfo(Integer lectureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postNotice(LectureNotice lecturenotice) {
		// TODO Auto-generated method stub
		return false;
	}

}
