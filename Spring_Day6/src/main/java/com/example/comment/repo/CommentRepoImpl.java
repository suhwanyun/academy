package com.example.comment.repo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.comment.dto.Comment;

@Repository
public class CommentRepoImpl implements CommentRepo {

	private final String NAME_SPACE = "spring.dao.CommentMapper.";
	
	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public int insert(Comment comment) {
		String stmt = NAME_SPACE + "insert";
		return template.insert(stmt, comment);
	}

}
