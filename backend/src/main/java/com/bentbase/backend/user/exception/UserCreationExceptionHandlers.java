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
import org.springframework.util.Assert;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public enum UserCreationExceptionHandlers {
	CONSTRAINT_VIOLATION_EXCEPTION_HANDLER(exception -> {
		try {
			Assert.isTrue(exception.getCause() instanceof JpaSystemException, "");
			Assert.isTrue(exception.getCause()
			                       .getCause() instanceof IdentifierGenerationException, "");
		} catch (Exception ignored) {
			return null;
		}
		
		var errors = new ArrayList<>(List.of("email: must not be blank"));
		errors.addAll(exception.getErrors());
		
		var response = new ExceptionResponse(exception.getMessage(), errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}),
	
	NONEXISTENT_ID_EXCEPTION_HANDLER(exception -> {
		try {
			Assert.isTrue(exception.getCause() instanceof TransactionSystemException, "");
			Assert.isTrue(exception.getCause()
			                       .getCause() instanceof RollbackException, "");
			Assert.isTrue(exception.getCause()
			                       .getCause()
			                       .getCause() instanceof ConstraintViolationException, "");
		} catch (Exception ignored) {
			return null;
		}
		
		var violations = ConstraintViolationExceptionUtil.getViolations((ConstraintViolationException) exception.getCause()
		                                                                                                        .getCause()
		                                                                                                        .getCause());
		violations.addAll(exception.getErrors());
		var response = new ExceptionResponse(exception.getMessage(), violations);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}),
	
	DEFAULT_EXCEPTION_HANDLER(exception -> {
		var response = new ExceptionResponse(exception.getMessage(), exception.getErrors());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	});
	
	@Getter
	private final ExceptionHandlerFunctor exceptionHandler;
	
	UserCreationExceptionHandlers(ExceptionHandlerFunctor exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
}
