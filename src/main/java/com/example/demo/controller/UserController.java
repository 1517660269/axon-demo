package com.example.demo.controller;

import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.user.coreapi.command.RegisterUserCommand;
import com.example.demo.controller.api.UserApi;

@RestController
public class UserController {

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody @Valid UserApi userApi) {
		String userId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new RegisterUserCommand(userId, userApi.getMobile(), userApi.getUsername(), userApi.getPassword(), userApi.getVerificationCode()));
		return ResponseEntity.created(URI.create(userId)).build();
	}


}
