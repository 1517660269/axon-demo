package com.example.demo.query.order.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.order.events.OrderCreatedEvent;
import com.example.demo.query.order.model.Order;
import com.example.demo.query.order.model.OrderItem;
import com.example.demo.query.order.repository.OrderRepository;
import com.example.demo.query.product.model.Product;
import com.example.demo.query.product.repository.ProductRepository;

@Component
public class OrderEventHandler {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@EventHandler
	public void on(OrderCreatedEvent event) {
		Order order = new Order();
		order.setOrderId(event.getOrderId());
		List<OrderItem> list = new ArrayList<>();
		event.getProducts().forEach((productId, number) -> {
			Product product = this.productRepository.findById(productId).get();

			OrderItem orderItem = new OrderItem();
			orderItem.setId(UUID.randomUUID().toString());
			orderItem.setNumber(number);
			orderItem.setProduct(product);

			order.setTotalPrice(order.getTotalPrice() + number * product.getPrice());
			list.add(orderItem);
		});
		order.setOrders(list);
		this.orderRepository.save(order);
	}

}
