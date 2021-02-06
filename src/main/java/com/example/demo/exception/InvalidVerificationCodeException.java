package com.example.demo.exception;

public class InvalidVerificationCodeException extends RuntimeException {
	private static final long serialVersionUID = -2958599969530469448L;

	public InvalidVerificationCodeException() {
	}

	public InvalidVerificationCodeException(String message) {
		super(message);
	}

	public InvalidVerificationCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidVerificationCodeException(Throwable cause) {
		super(cause);
	}
}
