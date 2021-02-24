package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.order.commands.CreateOrderCommand;
import com.example.demo.controller.api.OrderApi;
import com.example.demo.query.order.coreapi.query.FindOrderByUserIdQuery;
import com.example.demo.query.order.model.Order;
import com.example.demo.security.CurrentUserId;
import com.example.demo.security.PassToken;
import com.example.demo.security.UserLoginToken;

@RestController
public class OrderController {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryGateway queryGateway;

	@PostMapping("/order")
	@UserLoginToken
	public ResponseEntity<Void> createOrder(@CurrentUserId String currentUserId, @RequestBody @Valid OrderApi orderApi) {
		String orderId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new CreateOrderCommand(orderId, orderApi.getName(), orderApi.getMobile(), orderApi.getAddress(), orderApi.getTotalPrice(), orderApi.getProducts(), currentUserId));
		return ResponseEntity.created(URI.create("/order/" + orderId)).build();
	}

	@GetMapping("/order")
	@UserLoginToken
	public ResponseEntity<Object> myOrder(@CurrentUserId String currentUserId) {
		List<Order> orders = this.queryGateway.query(new FindOrderByUserIdQuery(currentUserId), List.class).join();
		return ResponseEntity.ok(orders);
	}
}
