package com.example.demo.controller;

import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.command.product.commands.CreateProductCommand;
import com.example.demo.controller.api.ProductAPI;

@RestController
public class ProductController {

	@Autowired
	private CommandGateway commandGateway;

	@PostMapping("/product")
	public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductAPI productAPI) {
		String productId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new CreateProductCommand(productId, productAPI.getName(), productAPI.getPrice(), productAPI.getStock(), productAPI.getIntroduction()));
		return ResponseEntity.created(URI.create("/product/" + productId)).build();
	}

}
