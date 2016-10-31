package academy.group5.exception;

/*@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class ManagerLoginException extends RuntimeException{
	private static final String DEFAULT_MSG = "잘못된 접근입니다";
	
	public ManagerLoginException(){
		super(DEFAULT_MSG);
	}
	
	public ManagerLoginException(String msg){
		super(msg);
	}
}
