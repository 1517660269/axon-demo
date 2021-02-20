package com.example.demo.query.user.coreapi.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.user.coreapi.event.UserProfileChangedEvent;
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

	@EventHandler
	public void on(UserProfileChangedEvent event) {
		User user = this.userRepository.findById(event.getId()).get();
		user.setUsername(event.getUsername());
		user.setRealName(event.getRealName());
		user.setDeliveryAddress(event.getDeliveryAddress());
	}
}
