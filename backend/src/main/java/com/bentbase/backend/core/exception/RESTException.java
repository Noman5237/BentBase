package com.bentbase.backend.core.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RESTException extends Exception {
	
	private final Map<String, String> errors = new HashMap<>();
	private final Map<String, Object> payloads = new HashMap<>();
	
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
	
	public RESTException withError(String key, String error) {
		this.errors.put(key, error);
		return this;
	}
	
	public RESTException withPayload(String key, Object payload) {
		this.payloads.put(key, payload);
		return this;
	}
	
}
