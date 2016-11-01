package academy.group5.exception;

/**
 * redirect 처리용 Exception
 * @author YSH
 *
 */
public class PageRedirectException extends RuntimeException{
	
	public PageRedirectException(){
		super();
	}
	
	public PageRedirectException(String msg){
		super(msg);
	}
}
