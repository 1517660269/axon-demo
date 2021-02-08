package com.example.demo.exception;

public class LoginFailedException extends RuntimeException {

	private static final long serialVersionUID = -2080854412425523532L;

	public LoginFailedException() {
		super();
	}

	public LoginFailedException(String message) {
		super(message);
	}

	public LoginFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginFailedException(Throwable cause) {
		super(cause);
	}
}
