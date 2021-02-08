package com.example.demo.controller.api;

import javax.validation.constraints.NotBlank;

public class LoginUserApi {

	@NotBlank
	private String account;

	@NotBlank
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
