package com.bentbase.backend.core.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FallbackExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(FallbackExceptionHandler.class);
	
	public static ResponseEntity<ExceptionResponse> respond(Exception exception) {
		logger.error("unhandled exception", exception);
		return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
