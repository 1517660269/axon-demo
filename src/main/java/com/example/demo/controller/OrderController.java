package com.example.demo.controller;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.order.commands.CreateOrderCommand;

@RestController
public class OrderController {

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping("/order")
	public ResponseEntity<Void> createOrder(@RequestBody Map<String, Integer> products) {
		String orderId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new CreateOrderCommand(orderId, products));
		return ResponseEntity.created(URI.create("/order/" + orderId)).build();
	}

}
