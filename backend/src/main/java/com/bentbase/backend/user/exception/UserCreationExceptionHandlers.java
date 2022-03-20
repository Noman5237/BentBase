package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.ExceptionHandlerFunctor;
import com.bentbase.backend.utils.ConstraintViolationExceptionUtil;
import lombok.Getter;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public enum UserCreationExceptionHandlers {
	CONSTRAINT_VIOLATION_EXCEPTION_HANDLER(exception -> {
		var conditions = new HashSet<>(Arrays.asList(exception.getCause() instanceof JpaSystemException,
		                                             exception.getCause()
		                                                      .getCause() instanceof IdentifierGenerationException));
		
		if (conditions.contains(false)) {
			return null;
		}
		
		var response = new ExceptionResponse(exception.getMessage(), List.of("email: must not be blank"));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}),
	
	NONEXISTENT_ID_EXCEPTION_HANDLER(exception -> {
		
		var conditions = new HashSet<>(Arrays.asList(exception.getCause() instanceof TransactionSystemException,
		                                             exception.getCause()
		                                                      .getCause() instanceof RollbackException,
		                                             exception.getCause()
		                                                      .getCause()
		                                                      .getCause() instanceof ConstraintViolationException));
		
		if (conditions.contains(false)) {
			return null;
		}
		
		var violations = ConstraintViolationExceptionUtil.getViolations((ConstraintViolationException) exception.getCause()
		                                                                                                        .getCause()
		                                                                                                        .getCause());
		
		var response = new ExceptionResponse(exception.getMessage(), violations);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	});
	
	@Getter
	private final ExceptionHandlerFunctor exceptionHandler;
	
	UserCreationExceptionHandlers(ExceptionHandlerFunctor exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
}
