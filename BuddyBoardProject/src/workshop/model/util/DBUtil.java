package workshop.model.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Connection 관리 및 의미없는 예외 처리를 간편히 하기 위한 기능성 메서드로 구성된 클래스
 * 
 * @author andy
 *
 */
public class DBUtil {
	static Logger logger = LoggerFactory.getLogger(DBUtil.class);

	private DBUtil() {
		init();
	}
	private static DBUtil util = new DBUtil();
	public static DBUtil getDBUtil() {
		return util;
	}

	// init -> config.xml파일 로딩 -> factory생성

	private SqlSessionFactory factory;
	private void init() {
		// 환경설정 xml파일 위치
		String res = "config/mybatis-config.xml";
		try {
			InputStream input = Resources.getResourceAsStream(res);
			// FactoryBuilder를 통해 Factory생성.
			
			factory = new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SqlSession getSession(){
		return factory.openSession();
	}

	


}
