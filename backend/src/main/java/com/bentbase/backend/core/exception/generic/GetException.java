package com.bentbase.backend.core.exception.generic;

import com.bentbase.backend.core.exception.RESTException;

public class GetException extends RESTException {
	
	public GetException(Class<?> entity) {
		super(getMessage(entity));
	}
	
	public GetException(Class<?> entity, Throwable cause) {
		super(getMessage(entity), cause);
	}
	
	private static String getMessage(Class<?> entity) {
		return "failed to find " + entity.getSimpleName() + "(s)";
	}
	
}
