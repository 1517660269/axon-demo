package com.example.demo.command.product.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.command.product.commands.CreateProductCommand;
import com.example.demo.command.product.commands.ReduceProductStockCommand;
import com.example.demo.command.product.events.ProductCreatedEvent;
import com.example.demo.command.product.events.ProductStockReducedEvent;
import com.example.demo.exception.ProductStockInsufficientException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductAggregateTest {

	private FixtureConfiguration<ProductAggregate> fixture;

	@BeforeEach
	void init() {
		fixture = new AggregateTestFixture<>(ProductAggregate.class);
	}

	@Test
	public void createProductCommand() {
		fixture.givenNoPriorActivity()
				.when(new CreateProductCommand("productId", "shirt", 55.8, 50, "introduction"))
				.expectSuccessfulHandlerExecution()
				.expectEvents(new ProductCreatedEvent("productId", "shirt", 55.8, 50, "introduction"))
				.expectState(productAggregate -> assertEquals(50, productAggregate.getStock()));
	}

	@Test
	public void reduceProductStockCommand() {
		fixture.given(new ProductCreatedEvent("productId", "shirt", 55.8, 50, "introduction"))
				.when(new ReduceProductStockCommand("productId", 4))
				.expectSuccessfulHandlerExecution()
				.expectEvents(new ProductStockReducedEvent("productId", 46))
				.expectState(aggregate -> assertEquals(46, aggregate.getStock()));


		fixture.given(new ProductCreatedEvent("productId", "shirt", 55.8, 50, "introduction"))
				.when(new ReduceProductStockCommand("productId", 50))
				.expectSuccessfulHandlerExecution()
				.expectEvents(new ProductStockReducedEvent("productId", 0))
				.expectState(aggregate -> assertEquals(0, aggregate.getStock()));
	}

	@Test
	public void reduceProductStockCommandException() {
		fixture.given(new ProductCreatedEvent("productId", "shirt", 55.8, 50, "introduction"))
				.when(new ReduceProductStockCommand("productId", 51))
				.expectException(ProductStockInsufficientException.class);

	}
}
