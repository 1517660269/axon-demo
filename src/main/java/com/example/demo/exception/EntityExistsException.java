package com.example.demo.exception;

public class EntityExistsException extends RuntimeException {

	private static final long serialVersionUID = -8546922814594300408L;

	public EntityExistsException() {
	}

	public EntityExistsException(String message) {
		super(message);
	}

	public EntityExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityExistsException(Throwable cause) {
		super(cause);
	}
}
