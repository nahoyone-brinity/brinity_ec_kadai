package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	// SELECT * FROM items WHERE category_id = ?
	List<Item> findByCategoryId(Integer categoryId);
	// SELECT * FROM items ORDER BY price ASC = ?
	List<Item> findAllByOrderByPriceAsc();
	// SELECT * FROM items ORDER BY price DESC = ?
	List<Item> findAllByOrderByPriceDesc();
	// SELECT * FROM items WHERE name LIKE = ?
	List<Item> findByNameContaining(String name);
	
}

