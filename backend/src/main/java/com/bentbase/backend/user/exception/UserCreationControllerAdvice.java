package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.ExceptionControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class UserCreationControllerAdvice extends ExceptionControllerAdvice {
	
	public UserCreationControllerAdvice() {
		Arrays.asList(UserCreationExceptionHandlers.values())
		      .forEach(handler -> super.handlers.add(handler.getExceptionHandler()));
	}
	
	@Override
	@ExceptionHandler (UserCreationException.class)
	public ResponseEntity<ExceptionResponse> handle(Exception exception) throws Exception {
		return super.handle(exception);
	}
}
