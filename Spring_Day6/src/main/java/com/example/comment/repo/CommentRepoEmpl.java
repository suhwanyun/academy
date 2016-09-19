package com.example.comment.repo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.example.comment.dto.Comment;

@Repository
public class CommentRepoEmpl implements CommentRepo {

	@Override
	@Bean
	public int insert(Comment comment) {
		
		return 0;
	}

}
