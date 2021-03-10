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

import com.example.demo.command.product.commands.CreateProductCommand;
import com.example.demo.controller.api.ProductApi;
import com.example.demo.query.product.coreapi.query.FindAllProductsQuery;
import com.example.demo.query.product.model.Product;
import com.example.demo.security.UserLoginToken;

@RestController
public class ProductController {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private QueryGateway queryGateway;

	@PostMapping("/product")
	@UserLoginToken
	public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductApi productAPI) {
		String productId = UUID.randomUUID().toString();
		this.commandGateway.sendAndWait(new CreateProductCommand(productId, productAPI.getName(), productAPI.getPrice(), productAPI.getStock(), productAPI.getIntroduction()));
		return ResponseEntity.created(URI.create("/product/" + productId)).build();
	}


	@GetMapping("/product")
	@UserLoginToken
	public ResponseEntity<Object> queryAll() {
		List<Product> list = this.queryGateway.query(new FindAllProductsQuery(), List.class).join();
		return ResponseEntity.ok(list);
	}
}
