package com.bentbase.backend.core.exception.generic;

import com.bentbase.backend.core.exception.RESTException;

public class UpdateException extends RESTException {
	
	public UpdateException(Class<?> entity) {
		super(getMessage(entity));
	}
	
	public UpdateException(Class<?> entity, Throwable cause) {
		super(getMessage(entity), cause);
	}
	
	private static String getMessage(Class<?> entity) {
		return "failed to update " + entity.getSimpleName();
	}
	
}
