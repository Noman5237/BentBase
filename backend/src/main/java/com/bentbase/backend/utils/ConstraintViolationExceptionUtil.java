package com.bentbase.backend.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

public class ConstraintViolationExceptionUtil {
	
	public static List<String> getViolations(ConstraintViolationException exception) {
		return exception.getConstraintViolations()
		                .stream()
		                .map(violation -> String.format("%s: %s", violation.getPropertyPath(), violation.getMessage()))
		                .collect(Collectors.toList());
	}
}
