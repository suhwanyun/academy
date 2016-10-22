package academy.group5.beans;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/config/dbconfig.properties")
public class JDBCDataSource {
	
	@Bean
	public DataSource dataSource(
			@Value("${db.driverClassName}") String driver,
			@Value("${db.url}") String url,
			@Value("${db.username}") String userName,
			@Value("${db.password}") String password){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(userName);
		ds.setPassword(password);
		
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds){
		return new JdbcTemplate(ds);
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource ds){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		String loc = "/config/mybatis-config.xml";
		bean.setDataSource(ds);
		bean.setConfigLocation(new ClassPathResource(loc));
		return bean;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sfac) throws Exception{
		SqlSessionTemplate template = new SqlSessionTemplate(sfac.getObject());
		return template;
		
	}
}
