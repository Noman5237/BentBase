package com.bentbase.backend.core.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface ExceptionHandlerFunctor {
	
	ResponseEntity<ExceptionResponse> handle(RESTException exception);
}
