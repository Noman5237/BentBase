package com.bentbase.backend.order;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllOrders(@RequestParam (defaultValue = "0") int page,
	                                        @RequestParam (defaultValue = "10") int size,
	                                        @RequestParam (required = false, defaultValue = "id,asc") String[] sorts) {
		
		Page<Order> ordersPage = orderService.getAllOrders(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(ordersPage);
	}
	
	@GetMapping ("/{id}")
	public Order getOrderById(@PathVariable ("id") Long id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping ("/create")
	public Order createOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}
	
	@PatchMapping ("/update")
	public Order updateOrder(@RequestBody Map<String, Object> properties) {
		return orderService.updateOrder(properties);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteOrderById(@PathVariable ("id") Long id) {
		orderService.deleteOrderById(id);
		return String.format("order with id: `%s` is deleted", id);
	}
}
