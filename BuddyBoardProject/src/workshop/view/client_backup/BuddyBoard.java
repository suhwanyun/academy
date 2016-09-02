package workshop.view.client_backup;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import workshop.model.dto.Board;
import workshop.model.dto.Customer;
import workshop.model.dto.Reply;
import workshop.controller.exception.ServiceFailException;
import workshop.controller.exception.WrongOrderException;
import workshop.controller.service.BoardService;
import workshop.controller.service.BoardServiceStub;

public class BuddyBoard {
	private final String line = "********************************************************************";
	Scanner scanner = new Scanner(System.in);
	private Map<String, Properties> menus = new HashMap<>();
	private Properties props = new Properties();
	private BoardService service = BoardServiceStub.getService();
	/**
	 * 게시물의 페이지번호
	 */
	private int currentPage = 1;

	private Customer currentUser;

	private Board currentBoard;

	/**
	 * menus.txt 파일에서 타이틀 및 메뉴 정보를 읽어들인 후 props 로 관리한다. menu 정보는 ,와 : 구분자에 의해
	 * 토큰화 처리한 후 다시 menus 로 관리한다.
	 */
	private BuddyBoard() {

		try {
			System.out.println(this.getClass().getResourceAsStream("menu.txt"));
			props.load(new InputStreamReader(this.getClass().getResourceAsStream("menu.txt"), "UTF-8"));
			Enumeration<Object> enume = props.keys();
			while (enume.hasMoreElements()) {
				String key = enume.nextElement().toString();
				if (key.startsWith("menu")) {
					String menu = props.getProperty(key);
					StringTokenizer tokens = new StringTokenizer(menu, ",:");
					Properties subProp = new Properties();
					while (tokens.hasMoreTokens()) {
						subProp.setProperty(tokens.nextToken().trim(), tokens.nextToken().trim());
					}
					menus.put(key, subProp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			BuddyBoard buddy = new BuddyBoard();
			buddy.displayMainMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 초기 intro 화면을 출력한다.
	 */
	private void displayMainMenu() {
		displayTitle("BuddyBoard에 오신것을 환영합니다.");
		String menu = null;
		do {
			this.displayHeader("title_main", "menu_main");
			try {
				menu = getOrderFromScannerAndJudge("menu_main");
				switch (menu) {
				case "J":
					this.displayJoinFrom();
					break;
				case "L":
					this.displayLoginForm();
					break;
				case "X":
					System.exit(0);
					break;
				}
			} catch (WrongOrderException w) {
				System.out.println(w.getMessage());
			}
		} while (true);

	}

	/**
	 * 회원가입할 수 있는 화면을 제공하고 service의 join 메서드를 이용해 가입처리한다. 사용되는 title의 key는
	 * title_join이며 shortkey의 key는 menu_join이다.
	 */
	private void displayJoinFrom() {
		do {
			this.displayTitle("title_join");
			System.out.println("ID를 10자 이내로 입력하세요.");
			String id = this.getDataFromScanner();
			System.out.println("이름을 10자 이내로 입력하세요.");
			String name = this.getDataFromScanner();
			System.out.println("비밀번호를 10자 이내로 입력하시오.");
			String pass = this.getDataFromScanner();

			System.out.printf("ID : %s, NAME : %s, PASS : %s 로 가입하시겠습니까?\n", id, name, pass);
			this.displayShortKey("menu_join");
			try {
				String order = getOrderFromScannerAndJudge("menu_join");
				if (order.equals("J")) {
					service.join(id, name, pass);
					break;
				} else if (order.equals("R")) {
					continue;
				} else {
					break;
				}
			} catch (WrongOrderException w) {
				System.out.println(w.getMessage());
			} catch (ServiceFailException e) {
				System.out.println("사용자 추가 실패 : " + e.getMessage());
			}
		} while (true);
	}

	
	/**
	 * 로그인할 수 있는 화면을 제공하고 service의 login 메서드를 이용해 로그인 처리한다. 사용되는 title의 key는
	 * title_login, shortkey의 key는 menu_login 이다. 로그인에 성공한 사용자는 맴버변수인
	 * currentUser에서 관리된다.
	 */
	private void displayLoginForm() {
		do {
			this.displayTitle("title_login");
			System.out.println("ID를 입력하세요.");
			String id = this.getDataFromScanner();
			System.out.println("비밀번호를 입력하시오.");
			String pass = this.getDataFromScanner();

			System.out.printf("ID : %s, PASS : %s 로 로그인하시겠습니까?\n", id, pass);
			this.displayShortKey("menu_login");
			try {
				String order = this.getOrderFromScannerAndJudge("menu_login");
				if (order.equals("L")) {
					currentUser = service.login(id, pass);
					displayBoardList();
					break;
				} else if (order.equals("R")) {
					continue;
				} else {
					break;
				}
			} catch (WrongOrderException w) {
				System.out.println(w.getMessage());
			} catch (ServiceFailException e) {
				System.out.println("로그인 실패 : " + e.getMessage());
			}
		} while (true);
	}

	/**
	 * 게시글의 목록을 화면에 출력한다. 출력할 제목의 key는 title_board이며 출력할 shortkey의 key는
	 * menu_board이다. 현재 화면에 보이는 페이지는 맴버변수인 currentPage에 의해 관리된다.
	 */
	private void displayBoardList() {
		do {
			this.displayTitle("title_board");
			try {
				List<Board> list = service.getBoardByPage(currentPage);
				if (list.size() == 0 && currentPage>1) {
					System.out.println("더 이상 글이 없습니다.");
					list = service.getBoardByPage(--currentPage);
				}
				System.out.println("글번호\t작성자 ID\t등록일");
				for (Board board : list) {
					System.out.println(board.getBoardNo() + "\t" + board.getCustomerCustId() + "\t" + board.getRegDate());
				}
				this.displayShortKey("menu_board");

				String order = this.getOrderFromScannerAndJudge("menu_board");
				if (order.equals("N")) {
					currentPage++;
				} else if (order.equals("P")) {
					currentPage = currentPage == 1 ? 1 : currentPage - 1;
				} else if (order.equals("W")) {
					writeNewBoard();
				} else if (order.equals("X")) {
					System.exit(0);
				} else {
					int commentId = Integer.parseInt(order);
					displayBoard(commentId);
				}
			} catch (WrongOrderException | ServiceFailException w) {
				System.out.println(w.getMessage());
			}
		} while (true);
	}

	/**
	 * 새로운 게시글을 등록한다. 출력할 제목의 key는 title_write_board, 단축키의 key는
	 * menu_write_board이다. scanner를 통해 여러 라인의 데이터를 입력받고 service의 writeBoard를
	 * 이용해서 저장한다.
	 */
	private void writeNewBoard() {
		do {
			this.displayTitle("title_write_board");
			try {
				String data = this.getMultiLineDataFromScanner();
				this.displayShortKey("menu_write_board");
				String menu = this.getOrderFromScannerAndJudge("menu_write_board");
				if (menu.equals("S")) {
					service.writeBoard(data, currentUser.getCustId());
					return;
				} else {
					return;
				}
			} catch (WrongOrderException | ServiceFailException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	/**
	 * boardNo에 해당하는 게시글을 화면에 출력한다. 출력할 제목의 key는 title_view_board, 단축키의 key는
	 * menu_view_board이다. 현재 보고 있는 게시글은 멤버변수인 currentBoard에 의해 관리된다.
	 * 
	 * @param boardNo
	 *            화면에 표시할 게시글 번호
	 */
	private void displayBoard(int boardNo) {
		do {
			this.displayTitle("title_view_board");
			Map<String, Object> map;
			try {
				map = service.getBoard(boardNo);
			} catch (ServiceFailException e) {
				System.out.println(e.getMessage());
				return;
			}
			
			Object boardObj = map.get("board");
			if (boardObj != null && boardObj instanceof Board) {
				this.currentBoard = (Board) boardObj;
			}

			System.out.println("글번호 : " + currentBoard.getBoardNo());
			System.out.println("작성자 : " + currentBoard.getCustomerCustId());
			System.out.println("작성일 : " + currentBoard.getRegDate());
			System.out.println("글내용 : \n" + currentBoard.getContent());
			System.out.println();

			Object replysObj = map.get("replys");
			if (replysObj != null && replysObj instanceof List<?>) {
				@SuppressWarnings("unchecked")
				List<Reply> replys = (List<Reply>) replysObj;
				System.out.println("댓글번호\t작성자\t작성일\t내용");
				if (replys.size() == 0) {
					System.out.println("등록된 댓글이 없습니다.");
				} else {
					for (Reply reply : replys) {
						System.out.println(reply.getReplyNo() + "\t" + reply.getCustomerCustId() + "\t" + reply.getRegDate() + "\t" + reply.getReplyContent());
					}
				}
			}

			this.displayShortKey("menu_view_board");
			try {
				String menu = this.getOrderFromScannerAndJudge("menu_view_board");
				if (menu.equals("R")) {
					writeNewReply();
				} else if (menu.equals("L")) {
					break;
				} else if (menu.equals("D")) {
					service.deleteBoard(currentBoard.getBoardNo());
					break;
				} else {
					try {
						int replyNo = Integer.parseInt(menu);
						service.deleteReply(replyNo);
					} catch (NumberFormatException n) {
						throw new WrongOrderException("숫자", menu);
					}
				}
			} catch (WrongOrderException | ServiceFailException w) {
				System.out.println(w.getMessage());
			}
		} while (true);
	}

	/**
	 * 새로운 댓글을 작성하는 메서드이다. 출력할 제목의 key는 title_write_reply, 단축키의 key는
	 * menu_write_reply이다. service가 제공하는 writeReply 메서드를 이용해 댓글을 등록한다.
	 */
	private void writeNewReply() {
		do {
			this.displayTitle("title_write_reply");
			try {
				String data = this.getMultiLineDataFromScanner();
				this.displayShortKey("menu_write_reply");
				String menu = getOrderFromScannerAndJudge("menu_write_reply");
				if (menu.equalsIgnoreCase("S")) {
					System.out.println(currentUser.getCustId() + " : " + currentBoard.getBoardNo());
					service.writeReply(data, currentUser.getCustId(), currentBoard.getBoardNo());
					return;
				} else {
					return;
				}
			} catch (WrongOrderException | ServiceFailException e) {
				System.out.println(e.getMessage());
			}
		} while (true);
	}

	/**
	 * scanner로부터 shortkey에 해당하는 요청을 입력받고 munuName에 의거해 제대로 된 요청인지 판단해서 검증된
	 * shortkey를 리턴한다. 부적절한 요청일 경우 WrongOrderException을 발생시킨다.
	 * 
	 * @param menu
	 *            사용될 menu의 이름으로 사용 가능한 shortkey 조회에 사용된다.
	 * @return 검증된 shrotkey
	 * @throws WrongOrderException
	 *             요청한 shrotkey가 해당 munu의 shortkey에 없을 경우 발생됨
	 */
	private String getOrderFromScannerAndJudge(String menu) throws WrongOrderException {
		String key = scanner.nextLine().trim().toUpperCase();
		Properties menuProps = menus.get(menu);
		if (menuProps == null) {
			throw new WrongOrderException();
		}
		if (menuProps.get(key) == null) {
			if (menuProps.get("숫자") != null && key.matches("[0-9]+")) {
				return key;
			}
			throw new WrongOrderException(menuProps.toString(), key);
		} else {
			key = key.toUpperCase();
		}
		return key;
	}

	/**
	 * scanner로부터 한줄의 문자열 데이터를 입력받아 리턴한다.
	 * 
	 * @return 입력받은 문자열
	 */
	private String getDataFromScanner() {
		return scanner.nextLine();
	}

	/**
	 * scanner를 통해 여러 줄의 데이터를 입력받아 결과를 리턴한다. 입력의 내용이 q와 다르면 계속 입력을 받고 q와 같다면
	 * 중지한다.
	 * 
	 * @return 입력받은 문자열
	 */
	private String getMultiLineDataFromScanner() {
		StringBuilder data = new StringBuilder();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (!line.trim().equalsIgnoreCase("q")) {
				data.append(line + "\n");
			} else {
				break;
			}
		}

		return data.toString();
	}

	/**
	 * 페이지별 헤더를 표시한다. 타이틀 목록을 가지고 있는 props에서 title로 조회한 후 값이 있으면 조회된 값을, 없으면
	 * 입력받은 title을 출력한다.
	 * 
	 * @param title
	 *            출력할 페이지의 제목
	 */
	private void displayTitle(String title) {
		System.out.println(line);
		String prop_title = props.getProperty(title);
		System.out.println(prop_title == null ? title : prop_title);
		System.out.println(line);
	}

	/**
	 * 페이지별 헤더를 표시한다. 타이틀 목록을 가지고 있는 props에서 title로 조회한 후 값이 있으면 조회된 값을, 없으면
	 * 입력받은 title을 출력한다. 타이틀 출력 후 menu에 해당하는 단축키를 출력한다.
	 * 
	 * @param title
	 *            출력할 페이지의 제목
	 * @param menu
	 *            출력할 페이지의 단축키
	 */
	private void displayHeader(String title, String menu) {
		System.out.println(line);
		String prop_title = props.getProperty(title);
		System.out.println(prop_title == null ? title : prop_title);
		displayShortKey(menu);
		System.out.println(line);
	}

	/**
	 * 사용할 단축키 목록을 보여준다. 키 목록을 가지고 있는 props에서 menu를 키로 조회한 후 값이 있으면 조회된 값을, 없으면
	 * 입력받은 menu를 출력한다.
	 * 
	 * @param menu
	 *            단축키를 조회할 키값
	 */
	private void displayShortKey(String menu) {
		String prop_menu = props.getProperty(menu);
		System.out.println(">>" + (prop_menu == null ? menu : prop_menu));
	}
}
