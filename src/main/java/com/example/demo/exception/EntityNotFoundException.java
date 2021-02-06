package com.example.demo.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -788299628614422933L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}
}
