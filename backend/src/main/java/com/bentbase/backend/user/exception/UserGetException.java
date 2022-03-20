package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.exception.RESTException;

public class UserGetException extends RESTException {
	
	private static final String MESSAGE = "failed to find user";
	
	public UserGetException() {
		super(MESSAGE);
	}
	
	public UserGetException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
}
