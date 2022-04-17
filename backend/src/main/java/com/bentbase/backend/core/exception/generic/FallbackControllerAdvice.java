package com.bentbase.backend.core.exception.generic;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.RESTExceptionControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class FallbackControllerAdvice extends RESTExceptionControllerAdvice {
	
	public FallbackControllerAdvice() {
		Arrays.asList(FallbackExceptionHandlers.values()).forEach(handler -> super.handlers.add(handler.getExceptionHandler()));
	}
	
	@Override
	@ExceptionHandler ({GetException.class, CreateException.class, UpdateException.class})
	public ResponseEntity<ExceptionResponse> handle(RESTException exception) {
		return super.handle(exception);
	}
}
