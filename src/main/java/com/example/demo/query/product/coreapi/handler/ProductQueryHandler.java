package com.example.demo.query.product.coreapi.handler;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.query.product.coreapi.query.FindAllProductsQuery;
import com.example.demo.query.product.repository.ProductRepository;

@Component
public class ProductQueryHandler {

	@Autowired
	private ProductRepository productRepository;

	@QueryHandler
	public List query(FindAllProductsQuery query) {
		return this.productRepository.findAll();
	}

}
