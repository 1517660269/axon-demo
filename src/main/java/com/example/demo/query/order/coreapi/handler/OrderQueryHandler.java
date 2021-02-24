package com.example.demo.query.order.coreapi.handler;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.query.order.coreapi.query.FindOrderByUserIdQuery;
import com.example.demo.query.order.model.Order;
import com.example.demo.query.order.repository.OrderRepository;

@Component
public class OrderQueryHandler {

	@Autowired
	private OrderRepository orderRepository;

	@QueryHandler
	public List<Order> query(FindOrderByUserIdQuery query) {
		return this.orderRepository.findOrdersByUser_Id(query.getUserId());
	}

}
