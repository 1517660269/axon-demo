package com.example.demo.query.user.coreapi.query;

public class FindUserByMobileOrUsernameQuery {

	private String account;

	public FindUserByMobileOrUsernameQuery(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

}
