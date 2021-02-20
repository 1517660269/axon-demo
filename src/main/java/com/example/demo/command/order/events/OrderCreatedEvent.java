package com.example.demo.command.order.events;

import java.util.Map;

public class OrderCreatedEvent {

	private String orderId;

	private String name;

	private String mobile;

	private String address;

	private double totalPrice;

	private Map<String, Integer> products;

	public OrderCreatedEvent(String orderId, String name, String mobile, String address, double totalPrice, Map<String, Integer> products) {
		this.orderId = orderId;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.totalPrice = totalPrice;
		this.products = products;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Map<String, Integer> getProducts() {
		return products;
	}
}
