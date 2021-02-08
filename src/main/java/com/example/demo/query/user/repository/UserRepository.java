package com.example.demo.query.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.query.user.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByMobile(String mobile);

	User findByUsername(String username);

	@Query(value = "select * from user_entry u where u.username =?1 or u.mobile=?1", nativeQuery = true)
	User findUserByUsernameOrMobile(String account);
}
