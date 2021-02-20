package com.example.demo.command.user.aggregate;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.command.user.coreapi.event.UserProfileChangedEvent;
import com.example.demo.command.user.coreapi.event.UserRegisteredEvent;

@Aggregate
public class UserAggregate {

	@AggregateIdentifier
	private String id;

	private String username;

	private String mobile;

	public UserAggregate() {
	}

	@EventSourcingHandler
	public void on(UserRegisteredEvent event) {
		this.id = event.getUserId();
		this.username = event.getUsername();
		this.mobile = event.getMobile();
	}

	@EventSourcingHandler
	public void on(UserProfileChangedEvent event) {
		this.username = event.getUsername();
	}

	public String getUsername() {
		return username;
	}

	public String getMobile() {
		return mobile;
	}
}
