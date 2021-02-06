package com.example.demo.controller.api;

import javax.validation.constraints.NotBlank;

public class UserApi {

	@NotBlank
	private String mobile;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String verificationCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
