package com.example.demo.command.order.aggregate;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.command.order.commands.CreateOrderCommand;
import com.example.demo.command.order.events.OrderCreatedEvent;
import com.example.demo.command.order.handler.OrderHandler;
import com.example.demo.command.product.aggregate.ProductAggregate;
import com.example.demo.command.product.events.ProductCreatedEvent;
import com.example.demo.exception.EntityNotFoundException;

public class OrderAggregateTest {

	private FixtureConfiguration<OrderAggregate> fixture;

	private FixtureConfiguration<ProductAggregate> productAggregateFixtureConfiguration;

	@BeforeEach
	void init() {
		fixture = new AggregateTestFixture<>(OrderAggregate.class);
		productAggregateFixtureConfiguration = new AggregateTestFixture<>(ProductAggregate.class);

		OrderHandler orderHandler = new OrderHandler();
		ReflectionTestUtils.setField(orderHandler, "repository", fixture.getRepository());
		ReflectionTestUtils.setField(orderHandler, "productAggregateRepository", productAggregateFixtureConfiguration.getRepository());

		fixture.registerAnnotatedCommandHandler(orderHandler);
	}

	@Test
	public void createOrderCommand() {
		Map<String, Integer> map = new HashMap<>();
		map.put("productId", 1);

		productAggregateFixtureConfiguration.given(new ProductCreatedEvent("productId", "shirt", 58, 50, ""));
		fixture.givenNoPriorActivity()
				.when(new CreateOrderCommand("orderId", "tom", "18888888888", "China", 58, map))
				.expectSuccessfulHandlerExecution()
				.expectEvents(new OrderCreatedEvent("orderId", "tom", "18888888888", "China", 58, map));

		map.put("productId", 50);
		fixture.givenNoPriorActivity()
				.when(new CreateOrderCommand("orderId", "tom", "18888888888", "China", 2940, map))
				.expectSuccessfulHandlerExecution()
				.expectEvents(new OrderCreatedEvent("orderId", "tom", "18888888888", "China", 2940, map));


	}

	@Test
	public void createOrderCommandThrowRuntimeExceptionByStock() {
		Map<String, Integer> map = new HashMap<>();
		map.put("productId", 100);

		productAggregateFixtureConfiguration.given(new ProductCreatedEvent("productId", "shirt", 58, 50, ""));
		fixture.givenNoPriorActivity()
				.when(new CreateOrderCommand("orderId", "tom", "18888888888", "China", 5800, map))
				.expectException(RuntimeException.class);

	}

	@Test
	public void createOrderCommandThrowRuntimeExceptionByNotFound() {
		Map<String, Integer> map = new HashMap<>();
		map.put("id", 10);

		fixture.givenNoPriorActivity()
				.when(new CreateOrderCommand("orderId", "tom", "18888888888", "China", 580, map))
				.expectException(EntityNotFoundException.class)
				.expectExceptionMessage("商品不存在");

	}
}
