package com.bentbase.backend.user.exception;

import com.bentbase.backend.core.exception.RESTException;

public class UserCreationException extends RESTException {
	
	public UserCreationException() {
	}
	
	public UserCreationException(Throwable cause) {
		super("failed to create new user", cause);
	}
	
}
