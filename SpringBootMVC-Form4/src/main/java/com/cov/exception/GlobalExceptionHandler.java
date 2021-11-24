package com.cov.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	public ResponseEntity<String> aaa(InvalidEmployeeIdException invalidEmployeeIdException) {
		String errorResponse = invalidEmployeeIdException.getMessage();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = InvalidEmployeeIdException.class)
	public ResponseEntity<ErrorResponse> invalidPersonIdHandler(InvalidEmployeeIdException invalidEmployeeIdException) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message =invalidEmployeeIdException .getMessage();
		ErrorResponse errorResponse = new ErrorResponse(status, message);
		return new ResponseEntity<>(errorResponse, status);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> otherHandler(Exception exception) {
		String message = exception.getMessage();
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
