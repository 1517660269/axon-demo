package com.example.demo.query.user.coreapi.query;

public class FindUserByIdQuery {

	private String currentUserId;

	public FindUserByIdQuery(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}
}
