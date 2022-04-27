package com.bentbase.backend.order;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface OrderService {
	
	Page<Order> getAllOrders(Paginate paginate);
	
	Order getOrderById(Long id);
	
	Order createOrder(Order order);
	
	Order updateOrder(Map<String, Object> properties);
	
	void deleteOrderById(Long id);
}
