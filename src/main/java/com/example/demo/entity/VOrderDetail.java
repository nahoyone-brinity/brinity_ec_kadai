package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_order_details")
public class VOrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 注文明細ID

	@Column(name = "order_id")
	private Integer orderId; // 注文ID

	@Column(name = "item_id")
	private Integer itemId; // 商品ID

	private Integer quantity; // 数量

	private String name; // 商品名

	private Integer price; // 商品価格（単価）

	public Integer getId() {
		return id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

}
