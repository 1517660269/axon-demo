package com.example.demo.command.user.coreapi.event;

public class UserProfileChangedEvent {

	private String id;

	private String username;

	private String realName;

	private String deliveryAddress;

	public UserProfileChangedEvent(String id, String username, String realName, String deliveryAddress) {
		this.id = id;
		this.username = username;
		this.realName = realName;
		this.deliveryAddress = deliveryAddress;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getRealName() {
		return realName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}
}
