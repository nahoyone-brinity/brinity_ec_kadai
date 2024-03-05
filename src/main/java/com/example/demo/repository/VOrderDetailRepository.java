package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VOrderDetail;

public interface VOrderDetailRepository extends JpaRepository<VOrderDetail, Integer> {

	// SELECT * FROM order_details WHERE order_id = ?
	List<VOrderDetail> findByOrderId(Integer id);
}
