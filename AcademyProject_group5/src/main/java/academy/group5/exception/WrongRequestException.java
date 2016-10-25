package academy.group5.exception;

/*@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class WrongRequestException extends RuntimeException{
	private static final String DEFAULT_MSG = "잘못된 접근입니다";
	
	public WrongRequestException(){
		super(DEFAULT_MSG);
	}
	
	public WrongRequestException(String msg){
		super(msg);
	}
}
