package com.example.demo.exception;

public class TokenException extends RuntimeException {
	private static final long serialVersionUID = 1557562235088056496L;

	public TokenException() {
		super();
	}

	public TokenException(String message) {
		super(message);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}
}
