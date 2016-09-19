package com.example.comment.repo;

import org.springframework.stereotype.Repository;

import com.example.comment.dto.User;

@Repository
public class UserRepoImpl implements UserRepo {
	
	@Override
	public int insert(User user) {
		
		return 0;
	}

}
