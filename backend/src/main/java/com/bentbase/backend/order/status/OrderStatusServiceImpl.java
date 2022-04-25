package com.bentbase.backend.order.status;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.user.User;
import com.bentbase.backend.user.UserServiceImpl;
import com.bentbase.backend.utils.PageUtil;
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

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final OrderStatusRepository orderStatusRepository;
	
	public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
		this.orderStatusRepository = orderStatusRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<OrderStatus> getAllOrderStatuses(PageUtil.Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
			                                                                          OrderStatus.class));
			return orderStatusRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(OrderStatus.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public OrderStatus getOrderStatusById(Long id) {
		Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
		if (orderStatus.isEmpty()) {
			throw new GetException(OrderStatus.class).withError("id", "does not exist");
		}
		
		return orderStatus.get();
	}
	
	@SneakyThrows
	@Override
	public OrderStatus getOrderStatusByName(String name) {
		Optional<OrderStatus> orderStatus = orderStatusRepository.findByName(name);
		if (orderStatus.isEmpty()) {
			throw new GetException(OrderStatus.class).withError("name", "does not exist");
		}
		
		return orderStatus.get();
	}
	
	@SneakyThrows
	@Override
	public OrderStatus createOrderStatus(OrderStatus orderStatus) {
		Optional<OrderStatus> existingOrderStatus = orderStatusRepository.findByName(orderStatus.getName());
		if (existingOrderStatus.isPresent()) {
			throw new CreateException(OrderStatus.class).withError("name", "already exists");
		}
		
		try {
			return orderStatusRepository.save(orderStatus);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(OrderStatus.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public OrderStatus updateOrderStatus(Map<String, Object> properties) {
		if (!properties.containsKey("id") && !properties.containsKey("name")) {
			throw new GetException(OrderStatus.class).withError("id", "must not be blank")
			                                               .withError("name", "must not be blank");
		}
		
		var orderStatus = properties.containsKey("id") ? getOrderStatusById(Long.valueOf((Integer) properties.get(
				"id"))) : getOrderStatusByName((String) properties.get("name"));
		
		try {
			PatchUtil.update(orderStatus, properties);
		} catch (RESTException exception) {
			throw new UpdateException(User.class, exception);
		}
		
		return orderStatusRepository.save(orderStatus);
	}
	
	@Override
	public void deleteOrderStatusById(Long id) {
		this.getOrderStatusById(id);
		orderStatusRepository.deleteById(id);
	}
	
	@Override
	public void deleteOrderStatusByName(String name) {
		this.getOrderStatusByName(name);
		orderStatusRepository.deleteByName(name);
	}
}
