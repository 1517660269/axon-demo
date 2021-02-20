package com.example.demo.controller.api;

import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderApi {

	@NotBlank
	private String name;

	@NotBlank
	private String mobile;

	@NotBlank
	private String address;

	@NotNull
	private Map<String, Integer> products;

	private double totalPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Integer> products) {
		this.products = products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
