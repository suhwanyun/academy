package com.example.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.comment.dto.Comment;
import com.example.comment.dto.User;
import com.example.comment.repo.CommentRepo;
import com.example.comment.repo.UserRepo;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo uRepo;
	
	@Autowired
	CommentRepo cRepo;
	
	@Override
	@Transactional
	public int join(User user) {
		// 사용자 정보 추가
		int result = uRepo.insert(user);
		// Comment 공지 추가
		Comment comment = new Comment(0, "admin", user.getUserId() + "님이 가입했어요.");
		result = cRepo.insert(comment);
		
		return result;
	}

}
