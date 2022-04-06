package com.bentbase.backend.utils;

import com.bentbase.backend.core.exception.RESTException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SortUtil {
	
	public static Sort getOrdersFromStringArray(String[] sorts, Class<?> context) throws RESTException {
		var orders = new ArrayList<Order>();
		if (!sorts[0].contains(",")) {
			String[] temp = sorts;
			sorts = new String[1];
			sorts[0] = String.format("%s,%s", temp[0], temp[1]);
		}
		
		var declaredFields = Arrays.stream(context.getDeclaredFields())
		                           .map(Field::getName)
		                           .collect(Collectors.toSet());
		var undefinedFields = new ArrayList<String>();
		for (String sort : sorts) {
			String[] _sort = sort.split(",");
			
			if (!declaredFields.contains(_sort[0])) {
				undefinedFields.add(_sort[0]);
				continue;
			}
			
			orders.add(new Order(Direction.fromString(_sort[1].toUpperCase()), _sort[0]));
		}
		
		if (undefinedFields.size() > 0) {
			throw new RESTException().withPayload("invalidAttributes", undefinedFields);
		}
		
		return Sort.by(orders);
	}
}
