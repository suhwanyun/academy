package academy.group5.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 강의 모델 클래스.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {

	/** 강의 ID. */
	private Integer lectureId;

	/** 강의 분반. */
	private Integer lectureClass;

	/** 강의 이름. */
	private String lectureName;

	/** 교수 이름. */
	private String professorName;
	
	/** 강의시간 목록 */
	private List<LectureTime> lecturetimeList;

	public Lecture(Integer lectureId, Integer lectureClass) {
		this(lectureId, lectureClass, null, null);
	}
	
	public Lecture(Integer lectureId, Integer lectureClass, String lectureName, String professorName) {
		super();
		this.lectureId = lectureId;
		this.lectureClass = lectureClass;
		this.lectureName = lectureName;
		this.professorName = professorName;
	}
	
	

/*	*//** 강의 신청 목록. *//*
	private Set<Lectureapply> lectureapplySet;

	*//** 강의 공지 목록. *//*
	private Set<Lecturenotice> lecturenoticeSet;*/
}
