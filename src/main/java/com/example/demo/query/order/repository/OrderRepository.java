package com.example.demo.query.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.query.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

	List<Order> findOrdersByUser_Id(String userId);

}
