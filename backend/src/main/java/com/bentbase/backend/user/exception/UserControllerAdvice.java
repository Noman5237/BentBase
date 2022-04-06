package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.RESTExceptionControllerAdvice;
import com.bentbase.backend.core.exception.RESTException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class UserControllerAdvice extends RESTExceptionControllerAdvice {
	
	public UserControllerAdvice() {
		Arrays.asList(UserExceptionHandlers.values())
		      .forEach(handler -> super.handlers.add(handler.getExceptionHandler()));
	}
	
	@Override
	@ExceptionHandler ({UserGetException.class, UserCreateException.class, UserUpdateException.class})
	public ResponseEntity<ExceptionResponse> handle(RESTException exception) {
		return super.handle(exception);
	}
}
