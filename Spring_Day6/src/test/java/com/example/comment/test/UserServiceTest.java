package com.example.comment.test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.comment.config.CommentConfig;
import com.example.comment.dto.Comment;
import com.example.comment.dto.User;
import com.example.comment.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CommentConfig.class})
@Sql({"/dbschema/comment_schema.sql", "/dbschema/comment_data.sql"})
public class UserServiceTest {

	@Autowired
	UserService service;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		assertThat(service, is(notNullValue()));
		User user = new User("hong2", "4321");
		assertThat(service.join(user), is(1));
	}

}
