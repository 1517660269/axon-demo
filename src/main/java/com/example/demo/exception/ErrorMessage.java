package com.example.demo.exception;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 2804967630122001376L;

	private int errorCode;

	private String errorMessage;

	public ErrorMessage() {
	}

	public ErrorMessage(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
