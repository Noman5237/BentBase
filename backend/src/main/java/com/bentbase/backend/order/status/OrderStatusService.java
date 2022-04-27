package com.bentbase.backend.order.status;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface OrderStatusService {
	
	Page<OrderStatus> getAllOrderStatuses(Paginate paginate);
	
	OrderStatus getOrderStatusById(Long id);
	
	OrderStatus getOrderStatusByName(String name);
	
	OrderStatus createOrderStatus(OrderStatus orderStatus);
	
	OrderStatus updateOrderStatus(Map<String, Object> properties);
	
	void deleteOrderStatusById(Long id);
	
	void deleteOrderStatusByName(String name);
}
