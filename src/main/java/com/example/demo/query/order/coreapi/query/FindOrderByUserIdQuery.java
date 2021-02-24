package com.example.demo.query.order.coreapi.query;

public class FindOrderByUserIdQuery {

	private String userId;

	public FindOrderByUserIdQuery(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
}
