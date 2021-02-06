package com.example.demo.query.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.query.user.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByMobile(String mobile);

	User findByUsername(String username);
}
