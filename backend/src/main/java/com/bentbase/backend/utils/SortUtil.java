package com.bentbase.backend.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

import java.util.ArrayList;

public class SortUtil {
	
	public static Sort getOrdersFromStringArray(String[] sorts) {
		var orders = new ArrayList<Order>();
		if (!sorts[0].contains(",")) {
			String[] temp = sorts;
			sorts = new String[1];
			sorts[0] = String.format("%s,%s", temp[0], temp[1]);
		}
		
		for (String sort : sorts) {
			String[] _sort = sort.split(",");
			orders.add(new Order(Direction.fromString(_sort[1].toUpperCase()), _sort[0]));
		}
		
		return Sort.by(orders);
	}
}
