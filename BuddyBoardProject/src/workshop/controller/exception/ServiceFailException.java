package workshop.controller.exception;
/**
 * Service가 실패했을 때 발생하는 Exception
 * @author andy
 *
 */
public class ServiceFailException extends Exception {
	/**
	 * 예외에서 사용할 메시지를 파라미터로 받는 생성자
	 * @param msg
	 */
	public ServiceFailException(String msg) {
		super(msg);
	}
	/**
	 * root 예외를 받아서 처리하는 생성자
	 * @param cause
	 */
	public ServiceFailException(Exception cause) {
		super(cause);
	}
}
