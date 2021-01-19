package com.example.demo.command.order.handler;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.order.aggregate.OrderAggregate;
import com.example.demo.command.order.commands.CreateOrderCommand;
import com.example.demo.command.order.events.OrderCreatedEvent;
import com.example.demo.command.product.aggregate.ProductAggregate;

@Component
public class OrderHandler {

	@Autowired
	private Repository<OrderAggregate> repository;

	@Autowired
	private Repository<ProductAggregate> productAggregateRepository;

	@CommandHandler
	public void handler(CreateOrderCommand command) throws Exception {
		Map<String, Integer> products = new HashMap<>();

		command.getProducts().forEach((productId, number) -> {
			try {
				Aggregate<ProductAggregate> load = this.productAggregateRepository.load(productId);
				Integer stock = load.invoke(ProductAggregate::getStock);
				if (number > stock)
					throw new IllegalArgumentException();

				products.put(productId, number);
			} catch (AggregateNotFoundException e) {
				e.printStackTrace();
			}
		});

		this.repository.newInstance(() -> {
			AggregateLifecycle.apply(new OrderCreatedEvent(command.getOrderId(), products));
			return new OrderAggregate();
		});
	}

}
