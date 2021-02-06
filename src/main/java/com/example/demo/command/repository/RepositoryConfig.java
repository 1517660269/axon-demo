package com.example.demo.command.repository;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.command.order.aggregate.OrderAggregate;
import com.example.demo.command.product.aggregate.ProductAggregate;
import com.example.demo.command.user.aggregate.UserAggregate;

@Configuration
public class RepositoryConfig {

	@Autowired
	private EventStore eventStore;

	@Bean
	public Repository<OrderAggregate> orderAggregateRepository() {
		return EventSourcingRepository.builder(OrderAggregate.class).eventStore(eventStore).build();
	}

	@Bean
	public Repository<ProductAggregate> productAggregateRepository() {
		return EventSourcingRepository.builder(ProductAggregate.class).eventStore(eventStore).build();
	}

	@Bean
	public Repository<UserAggregate> userAggregateRepository() {
		return EventSourcingRepository.builder(UserAggregate.class).eventStore(eventStore).build();
	}

}
