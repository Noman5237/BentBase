package com.bentbase.backend.core.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RESTControllerValidationExceptionHandler {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(RESTControllerValidationExceptionHandler.class);
	
	@ExceptionHandler ({MethodArgumentNotValidException.class})
	@ResponseStatus (HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleValidationError(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		if (exception.hasFieldErrors()) {
			for (FieldError error : exception.getFieldErrors()) {
				Map<String, String> transformedError = new HashMap<>();
				errors.put(error.getField(), error.getDefaultMessage());
			}
		}
		return new ExceptionResponse(null, errors);
	}
}
