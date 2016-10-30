package academy.group5.exception;

/*@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class MileageManagerException extends RuntimeException{
	private static final String DEFAULT_MSG = "잘못된 접근입니다";
	
	public MileageManagerException(){
		super(DEFAULT_MSG);
	}
	
	public MileageManagerException(String msg){
		super(msg);
	}
}
