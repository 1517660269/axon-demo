package com.example.demo.query.user.coreapi.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.user.coreapi.event.UserRegisteredEvent;
import com.example.demo.query.user.model.User;
import com.example.demo.query.user.repository.UserRepository;

@Component
public class UserEventHandler {

	@Autowired
	private UserRepository userRepository;

	@EventHandler
	public void on(UserRegisteredEvent event) {
		User user = new User();
		user.setId(event.getUserId());
		BeanUtils.copyProperties(event, user);
		this.userRepository.save(user);
	}
}
