package com.example.demo.command.order.commands;

import java.util.Map;

public class CreateOrderCommand {

	private String orderId;

	private Map<String, Integer> products;

	public CreateOrderCommand(String orderId, Map<String, Integer> products) {
		this.orderId = orderId;
		this.products = products;
	}

	public String getOrderId() {
		return orderId;
	}

	public Map<String, Integer> getProducts() {
		return products;
	}
}
