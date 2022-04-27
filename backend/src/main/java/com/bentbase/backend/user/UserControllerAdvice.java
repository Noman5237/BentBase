package com.bentbase.backend.user;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.FallbackControllerAdvice;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class UserControllerAdvice extends FallbackControllerAdvice {
	
	public UserControllerAdvice() {
		Arrays.asList(UserExceptionHandlers.values())
				.forEach(handler -> super.handlers.add(handler.getExceptionHandler()));
	}
	
	@Override
	@ExceptionHandler ({GetException.class, CreateException.class, UpdateException.class})
	public ResponseEntity<ExceptionResponse> handle(RESTException exception) {
		return super.handle(exception);
	}
}
