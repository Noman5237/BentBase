package com.bentbase.backend.order;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Map;
import java.util.Optional;

import static com.bentbase.backend.utils.PageUtil.Paginate;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	private final OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Order> getAllOrders(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
			                                                                          Order.class));
			return orderRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Order.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Order getOrderById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isEmpty()) {
			throw new GetException(Order.class).withError("id", "does not exist");
		}
		
		return order.get();
	}
	
	@SneakyThrows
	@Override
	public Order createOrder(Order order) {
		try {
			return orderRepository.save(order);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Order.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Order updateOrder(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Order.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var order = this.getOrderById(id);
		
		try {
			PatchUtil.update(order, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Order.class, exception);
		}
		
		return orderRepository.save(order);
	}
	
	@Override
	public void deleteOrderById(Long id) {
		this.getOrderById(id);
		orderRepository.deleteById(id);
	}
}
