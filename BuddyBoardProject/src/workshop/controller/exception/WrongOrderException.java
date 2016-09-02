package workshop.controller.exception;
/**
 * 콘솔 클라이언트에서 잘못된 명령이 입력되었을 때 처리하기 위한 예외 클래스
 * @author andy
 *
 */
public class WrongOrderException extends Exception {
	private String want;
	private String but;

	public WrongOrderException() {
		super("명령이 잘못되었습니다.");
	}

	public WrongOrderException(String want, String but) {
		super("명령이 잘못되었습니다.");
		this.want = want;
		this.but = but;
	}

	public WrongOrderException(String want, int but) {
		this(want, String.valueOf(but));
	}

	public String getMessage() {
		return "명령이 잘못 되었습니다. 가능 : (" + want + "), 입력 : " + but;
	}
}
