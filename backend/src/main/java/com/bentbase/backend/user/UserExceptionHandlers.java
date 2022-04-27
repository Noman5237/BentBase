package com.bentbase.backend.user;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.RESTExceptionHandlerFunctor;
import lombok.Getter;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.util.Assert;

public enum UserExceptionHandlers {
	NONEXISTENT_ID_EXCEPTION_HANDLER(exception -> {
		try {
			Assert.isTrue(exception.getCause() instanceof JpaSystemException, "");
			Assert.isTrue(exception.getCause()
					.getCause() instanceof IdentifierGenerationException, "");
		} catch (Exception ignored) {
			return null;
		}
		
		var errors = exception.getErrors();
		errors.put("email", "must not be blank");
		
		var response = new ExceptionResponse(exception.getMessage(), errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	});
	
	@Getter
	private final RESTExceptionHandlerFunctor exceptionHandler;
	
	UserExceptionHandlers(RESTExceptionHandlerFunctor exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
}
