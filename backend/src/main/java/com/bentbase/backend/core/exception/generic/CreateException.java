package com.bentbase.backend.core.exception.generic;

import com.bentbase.backend.core.exception.RESTException;

public class CreateException extends RESTException {
	
	public CreateException(Class<?> entity) {
		super(getMessage(entity));
	}
	
	public CreateException(Class<?> entity, Throwable cause) {
		super(getMessage(entity), cause);
	}
	
	private static String getMessage(Class<?> entity) {
		return "failed to create new " + entity.getSimpleName();
	}
	
}
