package com.bentbase.backend.core.exception.generic;

import com.bentbase.backend.core.dto.ExceptionResponse;
import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.RESTExceptionHandlerFunctor;
import com.bentbase.backend.utils.ConstraintViolationExceptionUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.Assert;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import java.util.List;

public enum FallbackExceptionHandlers {
	CONSTRAINT_VIOLATION_EXCEPTION_HANDLER(exception -> {
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
		violations.putAll(exception.getErrors());
		
		var response = new ExceptionResponse(exception.getMessage(), violations);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}),
	
	INVALID_ATTRIBUTE_EXCEPTION_HANDLER(exception -> {
		try {
			Assert.isTrue(exception.getCause() instanceof RESTException, "");
			Assert.isTrue(((RESTException) exception.getCause()).getPayloads()
					.containsKey("invalidAttributes"), "");
		} catch (Exception ignored) {
			return null;
		}
		
		//noinspection unchecked
		List<String> invalidAttributes = (List<String>) ((RESTException) exception.getCause()).getPayloads()
				.get("invalidAttributes");
		var errors = exception.getErrors();
		invalidAttributes.forEach(attribute -> errors.put(attribute, "invalid attribute"));
		
		var response = new ExceptionResponse(exception.getMessage(), errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	});
	
	@Getter
	private final RESTExceptionHandlerFunctor exceptionHandler;
	
	FallbackExceptionHandlers(RESTExceptionHandlerFunctor exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
}
