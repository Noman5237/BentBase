package com.bentbase.backend.utils;

import com.bentbase.backend.core.exception.RESTException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatchUtil {
	
	public static void update(Object target,
	                          Map<String, Object> properties) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, RESTException {
		
		Class<?> targetClass = target.getClass();
		var keys = properties.keySet();
		var declaredFields = Arrays.stream(targetClass.getDeclaredFields())
				.map(Field::getName)
				.collect(Collectors.toSet());
		final List<String> invalidAttributes = keys.stream()
				.filter(declaredFields::add)
				.collect(Collectors.toList());
		if (invalidAttributes.size() > 0) {
			throw new RESTException().withPayload("invalidAttributes", invalidAttributes);
		}
		
		var source = new ObjectMapper().convertValue(properties, targetClass);
		for (String key : keys) {
			var value = getValue(source, key);
			if (value != null) {
				setValue(target, key, value);
			}
		}
	}
	
	private static Object getValue(Object context,
	                               String key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return context.getClass()
				.getDeclaredMethod("get" + key.substring(0, 1)
						.toUpperCase() + key.substring(1), (Class<?>[]) null)
				.invoke(context);
	}
	
	private static void setValue(Object context,
	                             String key,
	                             Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		context.getClass()
				.getDeclaredMethod("set" + key.substring(0, 1)
						.toUpperCase() + key.substring(1), new Class[] {value.getClass()})
				.invoke(context, value);
	}
}
