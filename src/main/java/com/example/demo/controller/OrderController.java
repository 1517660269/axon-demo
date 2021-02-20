package com.example.demo.controller;

import java.net.URI;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.order.commands.CreateOrderCommand;
import com.example.demo.controller.api.OrderApi;
import com.example.demo.security.UserLoginToken;

@RestController
public class OrderController {

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping("/order")
	@UserLoginToken
	public ResponseEntity<Void> createOrder(@RequestBody OrderApi orderApi) {
		String orderId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new CreateOrderCommand(orderId, orderApi.getName(), orderApi.getMobile(), orderApi.getAddress(), orderApi.getTotalPrice(), orderApi.getProducts()));
		return ResponseEntity.created(URI.create("/order/" + orderId)).build();
	}

}
