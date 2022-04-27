package com.bentbase.backend.order.status;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/orderStatuses")
public class OrderStatusController {
	
	private final OrderStatusService orderStatusService;
	
	public OrderStatusController(OrderStatusService orderStatusService) {
		this.orderStatusService = orderStatusService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllOrderStatuses(@RequestParam (defaultValue = "0") int page,
	                                               @RequestParam (defaultValue = "10") int size,
	                                               @RequestParam (required = false, defaultValue = "name,asc") String[] sorts) {
		
		Page<OrderStatus> usersPage = orderStatusService.getAllOrderStatuses(new PageUtil.Paginate(
				page,
				size,
				sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(usersPage);
	}
	
	@GetMapping ("/id/{id}")
	public OrderStatus getOrderStatusById(@PathVariable ("id") Long id) {
		return orderStatusService.getOrderStatusById(id);
	}
	
	@GetMapping ("/name/{name}")
	public OrderStatus getOrderStatusByName(@PathVariable ("name") String name) {
		return orderStatusService.getOrderStatusByName(name);
	}
	
	@PostMapping ("/create")
	public OrderStatus createOrderStatus(@RequestBody OrderStatus orderStatus) {
		return orderStatusService.createOrderStatus(orderStatus);
	}
	
	@PatchMapping ("/update")
	public OrderStatus updateOrderStatus(@RequestBody Map<String, Object> properties) {
		return orderStatusService.updateOrderStatus(properties);
	}
	
	@DeleteMapping ("/id/{id}")
	public String deleteOrderStatusById(@PathVariable ("id") Long id) {
		orderStatusService.deleteOrderStatusById(id);
		return String.format("orderStatus with id: `%s` is deleted", id);
	}
	
	@DeleteMapping ("/name/{name}")
	public String deleteOrderStatusByName(@PathVariable ("name") String name) {
		orderStatusService.deleteOrderStatusByName(name);
		return String.format("orderStatus with name: `%s` is deleted", name);
	}
	
}
