package com.example.demo.query.product.handler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.command.product.events.ProductCreatedEvent;
import com.example.demo.command.product.events.ProductStockReducedEvent;
import com.example.demo.query.product.repository.ProductRepository;
import com.example.demo.query.product.model.Product;

@Component
public class ProductEventHandler {

	@Autowired
	private ProductRepository productRepository;

	@EventHandler
	public void on(ProductCreatedEvent event) {
		Product product = new Product();
		BeanUtils.copyProperties(event, product);
		this.productRepository.save(product);
	}

	@EventHandler
	public void on(ProductStockReducedEvent event) {
		Product product = this.productRepository.findById(event.getProductId()).get();
		product.setStock(event.getStock());
	}

}
