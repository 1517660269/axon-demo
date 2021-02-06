package com.example.demo.command.user.coreapi.event;

public class UserRegisteredEvent {

	private final String userId;

	private final String mobile;

	private final String username;

	private final String password;

	private final String verificationCode;

	public UserRegisteredEvent(String userId, String mobile, String username, String password, String verificationCode) {
		this.userId = userId;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.verificationCode = verificationCode;
	}

	public String getUserId() {
		return userId;
	}

	public String getMobile() {
		return mobile;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getVerificationCode() {
		return verificationCode;
	}
}
