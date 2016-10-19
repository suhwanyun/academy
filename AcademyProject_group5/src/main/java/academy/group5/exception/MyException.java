package academy.group5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyException extends RuntimeException{
	public MyException(){
		/*super("출력 내용");*/
	}
}
