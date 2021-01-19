package com.example.demo.command.order.events;

import java.util.Map;

public class OrderCreatedEvent {

	private String orderId;

	private Map<String, Integer> products;

	public OrderCreatedEvent(String orderId, Map<String, Integer> products) {
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
