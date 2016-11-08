package academy.group5.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import academy.group5.dto.NotificationSetting;
import academy.group5.dto.Posting;
import academy.group5.dto.UserData;
import academy.group5.dto.etc.MostRecommend;
import academy.group5.dto.etc.UserLectureTime;

@Repository
public class PhoneRepo {
	
	private final String PHONE_NS = "academy.repo.PhoneMapper.";
	
	@Autowired
	SqlSessionTemplate session;
	
	/** GCM 등록 */
	public int setGCMData(UserData userData) {
		String stmt = PHONE_NS + "gcmUpdate";
		return session.update(stmt, userData);
	}
	
	/** GCM 등록 해지 */
	public int resetGCMData(String userId) {
		String stmt = PHONE_NS + "gcmReset";
		return session.update(stmt, userId);
	}
	
	
	/** 알람 설정 정보 획득 */
	public List<NotificationSetting> getNotificationSettingList(String id) {
		String stmt = PHONE_NS + "selectNotificationSettingForPhone";
		return session.selectList(stmt, id);
	}
	
	/** 가장 추천을 많이 받은 글 탐색 */
	public Posting getMostRecommendPosting(MostRecommend searchData) {
		String stmt = PHONE_NS + "selectMostRecommendPostingForPhone";
		return session.selectOne(stmt, searchData);
	}
	
	/** 가장 최신 글 탐색 */
	public Posting getNewestPosting(String postingType) {
		String stmt = PHONE_NS + "selectNewestPostingForPhone";
		return session.selectOne(stmt, postingType);
	}
	
	/** 강의 시간 목록 획득 */
	public List<UserLectureTime> getLectureTimeList(String userId) {
		String stmt = PHONE_NS + "selectLectureTimeForPhone";
		return session.selectList(stmt, userId);
	}
}
