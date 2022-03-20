package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.exception.RESTException;

public class UserUpdateException extends RESTException {
	
	private static final String MESSAGE = "failed to update user";
	
	public UserUpdateException() {
		super(MESSAGE);
	}
	
	public UserUpdateException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
}
