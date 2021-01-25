package com.example.demo.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ErrorMessage handleEntityNotFoundException(EntityNotFoundException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	@ExceptionHandler(ProductStockInsufficientException.class)
	public ErrorMessage handleProductStockInsufficientException(ProductStockInsufficientException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	@ExceptionHandler(Exception.class)
	public ErrorMessage handleException(Exception e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}
}
