package com.example.demo.command.product.events;

public class ProductStockReducedEvent {

	private String productId;

	private Integer stock;

	public ProductStockReducedEvent(String productId, Integer stock) {
		this.productId = productId;
		this.stock = stock;
	}

	public String getProductId() {
		return productId;
	}

	public Integer getStock() {
		return stock;
	}
}
