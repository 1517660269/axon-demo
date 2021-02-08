package com.example.demo.controller;

import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.user.coreapi.command.RegisterUserCommand;
import com.example.demo.controller.api.LoginUserApi;
import com.example.demo.controller.api.UserApi;
import com.example.demo.controller.api.UserProfileApi;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.query.user.coreapi.query.FindUserByMobileOrUsernameQuery;
import com.example.demo.query.user.model.User;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.UserLoginToken;
import com.example.demo.utils.TokenUtil;

@RestController
public class UserController {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryGateway queryGateway;

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody @Valid UserApi userApi) {
		String userId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new RegisterUserCommand(userId, userApi.getMobile(), userApi.getUsername(), userApi.getPassword(), userApi.getVerificationCode()));
		return ResponseEntity.created(URI.create(userId)).build();
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid LoginUserApi loginUserApi) {
		User user = this.queryGateway.query(new FindUserByMobileOrUsernameQuery(loginUserApi.getAccount()), User.class).join();
		if (user == null)
			throw new LoginFailedException("用户名或密码错误");

		if (!user.getPassword().equals(loginUserApi.getPassword()))
			throw new LoginFailedException("用户名或密码错误");

		String token = TokenUtil.sing(loginUserApi.getAccount());
		return ResponseEntity.ok(token);
	}

	@UserLoginToken
	@GetMapping("/profile")
	public ResponseEntity<UserProfileApi> userProfile(@CurrentUser User user) {
		UserProfileApi api = new UserProfileApi();
		BeanUtils.copyProperties(user, api);
		return ResponseEntity.ok(api);
	}

}
