package com.example.demo.command.saga;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.test.saga.FixtureConfiguration;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.command.order.events.OrderCreatedEvent;
import com.example.demo.command.product.commands.ReduceProductStockCommand;
import com.example.demo.command.product.events.ProductStockReducedEvent;

public class OrderProductSagaTest {

	private FixtureConfiguration sagaFixture;

	@BeforeEach
	void init() {
		sagaFixture = new SagaTestFixture<>(OrderProductSaga.class);
	}

	@Test
	public void orderCreatedEvent() {
		Map<String, Integer> map = new HashMap<>();
		map.put("productId", 3);
		map.put("id", 5);

		sagaFixture.givenAggregate("orderId")
				.published()
				.whenPublishingA(new OrderCreatedEvent("orderId", map))
				.expectAssociationWith("productId", "productId")
				.expectAssociationWith("productId", "id")
				.expectDispatchedCommands(new ReduceProductStockCommand("productId", 3), new ReduceProductStockCommand("id", 5))
				.expectActiveSagas(1);

		//测试ProductStockReducedEvent
		sagaFixture.givenAggregate("productId")
				.published(new OrderCreatedEvent("orderId", map))
				.whenPublishingA(new ProductStockReducedEvent("productId", 3))
				.expectActiveSagas(0)
				.expectNoDispatchedCommands();

	}
}
