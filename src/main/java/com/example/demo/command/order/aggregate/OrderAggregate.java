package com.example.demo.command.order.aggregate;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.command.order.events.OrderCreatedEvent;

@Aggregate
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;

	@EventSourcingHandler
	void on(OrderCreatedEvent event) {
		this.orderId = event.getOrderId();
	}
}
