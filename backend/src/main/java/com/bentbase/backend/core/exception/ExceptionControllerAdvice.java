package com.bentbase.backend.core.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
	
	protected final List<ExceptionHandlerFunctor> handlers = new ArrayList<>();
	
	public ResponseEntity<ExceptionResponse> handle(RESTException exception) {
		ResponseEntity<ExceptionResponse> response;
		for (var handler : handlers) {
			response = handler.handle(exception);
			if (response != null) {
				return response;
			}
		}
		
		return FallbackExceptionHandler.respond(exception);
	}
}
