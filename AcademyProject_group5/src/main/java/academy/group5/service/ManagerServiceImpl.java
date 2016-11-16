package academy.group5.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import academy.group5.dto.Lecture;
import academy.group5.dto.LectureTime;
import academy.group5.dto.Manager;
import academy.group5.dto.MileageProduct;
import academy.group5.dto.Term;
import academy.group5.dto.etc.Paging;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.GCMRepo;
import academy.group5.repo.LectureRepo;
import academy.group5.repo.ManagerRepo;
import academy.group5.repo.TermRepo;
import academy.group5.util.GCM;
import academy.group5.util.ImageControl;
import academy.group5.util.MyHash;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	TermRepo termRepo;
	
	@Autowired
	ManagerRepo managerRepo;
	
	@Autowired
	LectureRepo lecRepo;
	
	@Autowired
	GCMRepo gcmRepo;
	
	@Autowired
	AutoService autoService;
	
	@Autowired
	LectureNoticeService lecNoticeService;
	
	/** 한 페이지에 표시되는 강의의 수 */
	private final int LECTURE_MAX_PAGE = 10;
	/** 한 페이지에 표시되는 마일리지 상품의 수 */
	private final int MILEAGE_PRODUCT_MAX_PAGE = 5;
	
	@Override
	public boolean isTermSetted(){
		if(termRepo.getNextTermStartDate() != null
				|| termRepo.getTodayTerm() != null){
			return true;
		}
		throw new WrongRequestException("강의 등록을 위해, 다음 학기를 등록해주세요");
		
	}
	
	@Override
	public boolean isNotTermSetted(){
		if(termRepo.getNextTermStartDate() != null
				|| termRepo.getTodayTerm() != null){
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
	public void registTerm(String termStartStr, String termEndStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// String -> Date 변환
		Date termStart = null;
		Date termEnd = null;
		try {
			termStart = format.parse(termStartStr);
			termEnd = format.parse(termEndStr);
		} catch (ParseException e) {
			throw new WrongRequestException();
		}
		
		Calendar cal = Calendar.getInstance();
		
		if(termStart == null || termEnd == null){
			throw new WrongRequestException();
		} else if(termStart.before(cal.getTime()) || termEnd.before(cal.getTime())){
			throw new WrongRequestException("현재 시간보다 이후로 설정해주세요");
		} else if(termEnd.before(termStart)){
			throw new WrongRequestException("학기 종료시간이 시작시간보다 앞설 수 없습니다.");
		}
		// 학기 종료날짜는 자정이 지난 다음날 0시로 설정
		cal.setTime(termEnd);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		termEnd = cal.getTime();
		
		Term term = new Term(termStart, termEnd);
		
		termRepo.deleteTerm();		
		int result = termRepo.setTerm(term);
		if(result != 1){
			throw new WrongRequestException();
		}
		// 반장 선거 스케줄러 시작
		autoService.startVoteScheduler();
		// 학기 종료 스케줄러 시작
		autoService.startTermScheduler();
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
		
		// 알람 내용
		String noticeTitle = "강의시간이 영구적으로 변경되었습니다.";
		String noticeContent = lecNoticeService.setNoticeContentByClass("", lectureTime);
		
		Lecture lectureData = lecRepo.getLectureByTimeId(lectureTime.getLectureTimeId());
		if(lectureData == null){
			throw new WrongRequestException();
		}
		String lectureName = lecRepo.getLectureName(lectureData);
		// 알람을 받을 핸드폰 정보들
		List<String> userIdList = gcmRepo.getLectureApplyUser(lectureData);
		// 메세지 PUSH
		new GCM(lectureName,
				noticeTitle, 
				noticeContent,
				userIdList,
				GCM.TYPE_SETTING_NOTICE);
		return true;
	}

	@Override
	public boolean deleteLecture(Integer lectureId, Integer lectureClass) {
		Lecture lectureData = new Lecture(lectureId, lectureClass);
		
		// 삭제 전 정보 획득(알람에 사용)
		String lectureName = lecRepo.getLectureName(lectureData);
		String noticeTitle = "강의가 폐강처리 되었습니다.";
		
		// 알람을 받을 핸드폰 정보들
		List<String> userIdList = gcmRepo.getLectureApplyUser(lectureData);
		
		// 강의 신청 목록 삭제
		managerRepo.deleteAllLectureApply(lectureData);
		// 강의 공지 목록 삭제
		managerRepo.deleteAllLectureNotice(lectureData);
		// 강의 시간 삭제
		managerRepo.deleteAllLectureTime(lectureData);
		
		// 강의 삭제
		int result = managerRepo.deleteLecture(lectureData);
		if(result != 1){
			throw new WrongRequestException();
		}
		
		// 메세지 PUSH
		new GCM(lectureName + " " + lectureData.getLectureClass() + "분반",
				noticeTitle, 
				"",
				userIdList,
				GCM.TYPE_SETTING_NOTICE);
		
		return true;
	}
	
	@Override
	public boolean deleteLectureTime(Integer lectureTimeId){
		// 삭제 전 정보 획득(알람에 사용)
		Lecture lectureData = lecRepo.getLectureByTimeId(lectureTimeId);
		if(lectureData == null){
			throw new WrongRequestException();
		}
		LectureTime timeData = lecRepo.getLectureTimeById(lectureTimeId);
		String lectureName = lecRepo.getLectureName(lectureData);
		
		// 알람 내용
		String noticeTitle = "강의 시간이 삭제처리 되었습니다.";
		String noticeContent = lecNoticeService.setNoticeContentByClass("", timeData);
		
		int result = managerRepo.deleteLectureTime(lectureTimeId);
		if(result != 1){
			throw new WrongRequestException();
		}
			
		// 알람을 받을 핸드폰 정보들
		List<String> userIdList = gcmRepo.getLectureApplyUser(lectureData);
		// 메세지 PUSH
		new GCM(lectureName + " " + lectureData.getLectureClass() + "분반",
				noticeTitle, 
				noticeContent,
				userIdList,
				GCM.TYPE_SETTING_NOTICE);
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
	
	@Override
	public List<MileageProduct> getAllProduct(int page, String orderType, boolean isAsc) {
		return managerRepo.getAllMileageProduct(new Paging(page, MILEAGE_PRODUCT_MAX_PAGE, null, null, null, orderType, isAsc));
	}
	
	@Override
	public int getMaxMileagePage(){
		int allCount = managerRepo.getAllProductCount();
		
		if(allCount % MILEAGE_PRODUCT_MAX_PAGE == 0){
			return allCount / MILEAGE_PRODUCT_MAX_PAGE;
		}
		return allCount / MILEAGE_PRODUCT_MAX_PAGE + 1;
	}
	
	@Override
	public MileageProduct getProduct(int productId) {
		MileageProduct productData = managerRepo.getMileageProduct(productId);
		if(productData == null) {
			throw new WrongRequestException();
		}
		productData.setProductContent(productData.getProductContent().replaceAll("<br>", "\n"));
		return productData;
	}
	
	@Override
	public boolean registerProduct(MileageProduct productData) {
		productData.setProductContent(productData.getProductContent().replaceAll("\n", "<br>"));
		int result = managerRepo.setMileageProduct(productData);
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}

	@Override
	public boolean updateProduct(MileageProduct productData) {
		productData.setProductContent(productData.getProductContent().replaceAll("\n", "<br>"));
		int result = managerRepo.updateMileageProduct(productData);
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

	/** 물품 이미지 업로드 */
	@Override
	public int upload(MultipartFile uploadData, MileageProduct productData){
		final String TYPE_MILEAGE_PRODUCT = "mileage";
		
		// 원본 파일명
		String originalName = uploadData.getOriginalFilename();
		// 파일 확장자 추출
		String fileType = originalName.substring(originalName.lastIndexOf(".") + 1, originalName.length());
		// 물품 번호
		Integer productId = productData.getProductId();
		if(productId == null){
			if((productId = managerRepo.getNewMileageProductId()) == null){
				throw new WrongRequestException();
			}
			productData.setProductId(productId);
		}
		// 파일명 : mileage + 물품번호 + 확장자
		String fileName = TYPE_MILEAGE_PRODUCT + "_" + productId + "." + fileType;	
		
		String tmpFilePath = PostingService.IMG_PATH + PostingService.TMP_PREFIX + fileName;
		String filePath = PostingService.IMG_PATH + fileName;
		String previewPath = PostingService.PREVIEW_IMG_PATH + fileName;
		
		File originalFile = new File(tmpFilePath);
		ImageControl imgCtrl = new ImageControl();
		// 파일 업로드
		try {
			uploadData.transferTo(originalFile);
			imgCtrl.imageResize(tmpFilePath, filePath, fileType);
			imgCtrl.previewImageResize(tmpFilePath, previewPath, fileType);
		} catch (IllegalStateException | IOException e) {
			return -1;
		} finally{
			// 임시 파일 삭제
			originalFile.delete();
		}
		
		productData.setProductImgfile(fileName);
		// DB에 저장된 파일의 이름을 등록
		if(!photoRegister(productData)){
			return -1;
		}
		
		return 0;
	}
	
	@Override
	public boolean photoRegister(MileageProduct productData) {
		int result = managerRepo.updateProductPhoto(productData);
		
		if(result != 1){
			throw new WrongRequestException();
		}
		return true;
	}
	
	/** 업로드 취소 */
	@Override
	public void uploadCancel(MileageProduct productData, String fileName){
		if(fileName == null){
			fileName = productData.getProductImgfile();		
		}

		String filePath = PostingService.IMG_PATH + fileName;
		String previewPath = PostingService.PREVIEW_IMG_PATH + fileName;
		
		new File(filePath).delete();
		new File(previewPath).delete();
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
