package com.example.demo.command.product.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.command.product.commands.CreateProductCommand;
import com.example.demo.command.product.commands.ReduceProductStockCommand;
import com.example.demo.command.product.events.ProductCreatedEvent;
import com.example.demo.command.product.events.ProductStockReducedEvent;
import com.example.demo.exception.ProductNotEnoughException;

@Aggregate
public class ProductAggregate {

	@AggregateIdentifier
	private String productId;

	private int stock;

	public ProductAggregate() {
	}

	@CommandHandler
	public ProductAggregate(CreateProductCommand command) {
		AggregateLifecycle.apply(new ProductCreatedEvent(command.getProductId(), command.getName(), command.getPrice(), command.getStock(), command.getIntroduction()));
	}

	@EventSourcingHandler
	void on(ProductCreatedEvent event) {
		this.productId = event.getProductId();
		this.stock = event.getStock();
	}

	@CommandHandler
	public void handler(ReduceProductStockCommand command) {
		if (command.getNumber() > stock)
			throw new ProductNotEnoughException("商品存库不足");
		AggregateLifecycle.apply(new ProductStockReducedEvent(command.getProductId(), this.stock - command.getNumber()));
	}

	@EventSourcingHandler
	void on(ProductStockReducedEvent event) {
		this.stock = event.getStock();
	}

	public int getStock() {
		return stock;
	}
}
