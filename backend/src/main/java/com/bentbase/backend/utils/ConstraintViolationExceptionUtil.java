package com.bentbase.backend.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

public class ConstraintViolationExceptionUtil {
	
	public static Map<String, String> getViolations(ConstraintViolationException exception) {
		return exception.getConstraintViolations()
		                .stream()
		                .collect(Collectors.toMap(violation -> violation.getPropertyPath()
		                                                                .toString(), ConstraintViolation::getMessage));
	}
}
