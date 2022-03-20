package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.exception.RESTException;

public class UserCreateException extends RESTException {
	
	private static final String MESSAGE = "failed to create new user";
	
	public UserCreateException() {
		super(MESSAGE);
	}
	
	public UserCreateException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
}
