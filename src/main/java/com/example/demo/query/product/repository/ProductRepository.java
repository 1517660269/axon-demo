package com.example.demo.query.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.query.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
