package com.example.demo.exception;

public class ProductStockInsufficientException extends RuntimeException {
	private static final long serialVersionUID = 4782546017996095441L;

	public ProductStockInsufficientException() {
	}

	public ProductStockInsufficientException(String message) {
		super(message);
	}

	public ProductStockInsufficientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductStockInsufficientException(Throwable cause) {
		super(cause);
	}
}
