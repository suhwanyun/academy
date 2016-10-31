package academy.group5.exception;

/*@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class LectureManagerException extends RuntimeException{
	private static final String DEFAULT_MSG = "잘못된 접근입니다";
	
	public LectureManagerException(){
		super(DEFAULT_MSG);
	}
	
	public LectureManagerException(String msg){
		super(msg);
	}
}
