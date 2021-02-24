package com.example.demo.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(ProductNotEnoughException.class)
	public ErrorMessage handleProductStockInsufficientException(ProductNotEnoughException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	@ExceptionHandler(EntityExistsException.class)
	public ErrorMessage handleEntityExistsException(EntityExistsException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.CONFLICT;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	@ExceptionHandler(InvalidVerificationCodeException.class)
	public ErrorMessage handleInvalidVerificationCodeException(InvalidVerificationCodeException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getMessage());
	}

	@ExceptionHandler(TokenException.class)
	public ErrorMessage handleTokenException(TokenException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	@ExceptionHandler(LoginFailedException.class)
	public ErrorMessage handleLoginFailedException(LoginFailedException e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}

	/*@ExceptionHandler(Exception.class)
	public ErrorMessage handleException(Exception e, HttpServletResponse response) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		response.setStatus(status.value());
		return new ErrorMessage(status.value(), e.getLocalizedMessage());
	}*/
}
