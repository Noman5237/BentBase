package com.bentbase.backend.user.exception;

public class UserCreationException extends Exception {
	
	public UserCreationException(Throwable cause) {
		super("failed to create new user", cause);
	}
	
}
