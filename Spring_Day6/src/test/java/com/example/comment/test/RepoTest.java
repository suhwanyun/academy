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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.comment.config.CommentConfig;
import com.example.comment.dto.Comment;
import com.example.comment.repo.CommentRepo;
import com.example.comment.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CommentConfig.class})
public class RepoTest {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CommentRepo commRepo;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SqlSessionTemplate template;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		assertThat(userRepo, is(notNullValue()));
		assertThat(commRepo, is(notNullValue()));
		
		assertThat(ds, is(notNullValue()));
		Connection con = ds.getConnection();
		assertThat(con, is(notNullValue()));
		assertThat(template, is(notNullValue()));
	}
	
	@Test
	public void insertTest(){
		Comment comment = new Comment(0, "hong", "test");
		int result = commRepo.insert(comment);
		assertThat(result, is(1));
	}

}
