package com.example.demo.command.product.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ReduceProductStockCommand {

	@TargetAggregateIdentifier
	private String productId;

	private Integer number;

	public ReduceProductStockCommand(String productId, Integer number) {
		this.productId = productId;
		this.number = number;
	}

	public String getProductId() {
		return productId;
	}

	public Integer getNumber() {
		return number;
	}
}
