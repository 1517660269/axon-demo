package com.example.demo.command.user.aggregate;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.command.user.coreapi.event.UserRegisteredEvent;

@Aggregate
public class UserAggregate {

	@AggregateIdentifier
	private String id;

	public UserAggregate() {
	}

	@EventSourcingHandler
	public void on(UserRegisteredEvent event) {
		this.id = event.getUserId();
	}
}
