package com.bentbase.backend.core.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class RESTExceptionControllerAdvice extends ResponseEntityExceptionHandler {
	
	@Getter
	protected final List<RESTExceptionHandlerFunctor> handlers = new ArrayList<>();
	
	public ResponseEntity<ExceptionResponse> handle(RESTException exception) {
		ResponseEntity<ExceptionResponse> response;
		for (var handler : handlers) {
			response = handler.handle(exception);
			if (response != null) {
				return response;
			}
		}
		response = handleDefault(exception);
		
		return response;
	}
	
	private ResponseEntity<ExceptionResponse> handleDefault(RESTException exception) {
		var errors = exception.getErrors().size() > 0 ? exception.getErrors() : null;
		var defaultExceptionResponse = new ExceptionResponse(exception.getMessage(), errors);
		return new ResponseEntity<>(defaultExceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
