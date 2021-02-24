package com.example.demo.exception;

public class ProductNotEnoughException extends RuntimeException {
	private static final long serialVersionUID = 4782546017996095441L;

	public ProductNotEnoughException() {
	}

	public ProductNotEnoughException(String message) {
		super(message);
	}

	public ProductNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotEnoughException(Throwable cause) {
		super(cause);
	}
}
