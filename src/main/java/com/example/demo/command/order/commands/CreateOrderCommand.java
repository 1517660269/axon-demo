package com.example.demo.command.order.commands;

import java.util.Map;

public class CreateOrderCommand {

	private String orderId;

	private String name;

	private String mobile;

	private String address;

	private double totalPrice;

	private Map<String, Integer> products;

	private String currentUserId;

	public CreateOrderCommand(String orderId, String name, String mobile, String address, double totalPrice, Map<String, Integer> products, String currentUserId) {
		this.orderId = orderId;
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.totalPrice = totalPrice;
		this.products = products;
		this.currentUserId = currentUserId;
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

	public String getCurrentUserId() {
		return currentUserId;
	}
}
