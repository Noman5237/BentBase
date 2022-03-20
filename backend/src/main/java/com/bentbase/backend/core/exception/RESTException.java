package com.bentbase.backend.core.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RESTException extends Exception {
	
	private final List<String> errors = new ArrayList<>();
	
	public RESTException() {
	}
	
	public RESTException(String message) {
		super(message);
	}
	
	public RESTException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RESTException(Throwable cause) {
		super(cause);
	}
	
	public RESTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public RESTException withErrors(String... errors) {
		this.errors.addAll(List.of(errors));
		return this;
	}
	
}
