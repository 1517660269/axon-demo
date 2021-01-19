package com.example.demo.query.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.query.order.modle.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
