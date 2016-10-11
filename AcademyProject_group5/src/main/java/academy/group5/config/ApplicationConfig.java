package academy.group5.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import academy.group5.repo.TermRepo;
import academy.group5.service.AutoService;
import academy.group5.service.AutoServiceImpl;

// Spring Core에 대한 설정
@Configuration
@ComponentScans({
	@ComponentScan("academy.group5.beans"),
	@ComponentScan("academy.group5.repo"),
	@ComponentScan("academy.group5.service"),
	@ComponentScan("academy.group5.controller"),
	@ComponentScan("academy.group5.scheduler")
})
@EnableTransactionManagement
public class ApplicationConfig {
	
	@Bean
	AutoService setAutoService(){
		return new AutoServiceImpl();
	}
	
	@Bean
	PlatformTransactionManager transactionManager(DataSource ds){
		return new DataSourceTransactionManager(ds);
	}
}
