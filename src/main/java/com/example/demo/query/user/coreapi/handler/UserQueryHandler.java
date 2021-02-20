package com.example.demo.query.user.coreapi.handler;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.query.user.coreapi.query.FindUserByIdQuery;
import com.example.demo.query.user.coreapi.query.FindUserByMobileOrUsernameQuery;
import com.example.demo.query.user.model.User;
import com.example.demo.query.user.repository.UserRepository;

@Component
public class UserQueryHandler {

	@Autowired
	private UserRepository userRepository;

	@QueryHandler
	public User query(FindUserByMobileOrUsernameQuery query) {
		return this.userRepository.findUserByUsernameOrMobile(query.getAccount());
	}

	@QueryHandler
	public User query(FindUserByIdQuery query) {
		return this.userRepository.findById(query.getCurrentUserId()).orElse(null);
	}
}
