package com.example.comment.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.example.comment.repo", "com.example.comment.beans", "com.example.comment.service"})
@EnableTransactionManagement
public class CommentConfig {
	@Bean
	public PlatformTransactionManager transactionManager(DataSource ds){
		return new DataSourceTransactionManager(ds);
	}
}
