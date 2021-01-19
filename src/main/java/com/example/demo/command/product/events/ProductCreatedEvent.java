package com.example.demo.command.product.events;

public class ProductCreatedEvent {

	private String productId;

	private String name;

	private double price;

	private int stock;

	private String introduction;

	public ProductCreatedEvent(String productId, String name, double price, int stock, String introduction) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.introduction = introduction;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public String getIntroduction() {
		return introduction;
	}

}
