package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	// SELECT * FROM orders WHERE customer_id = ?
	List<Order> findByCustomerId(Integer id);
}
