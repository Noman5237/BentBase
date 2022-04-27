package com.bentbase.backend.user;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.utils.PageUtil.Paginate;
import com.bentbase.backend.utils.PatchUtil;
import com.bentbase.backend.utils.SortUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.bentbase.backend.user.UserSpecifications.filterBy;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<User> getAllUsers(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(), User.class));
			return userRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(User.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new GetException(User.class).withError("email", "does not exist");
		}
		
		return user.get();
	}
	
	@SneakyThrows
	@Override
	public Page<User> filterUsers(User filter, Paginate paginate) {
		PageRequest pagination = PageRequest.of(paginate.getPage(),
		                                        paginate.getSize());
		return userRepository.findAll(filterBy(filter), pagination);
	}
	
	@SneakyThrows
	@Override
	public User updateUser(Map<String, Object> properties) {
		if (!properties.containsKey("email")) {
			throw new GetException(User.class).withError("email", "must not be blank");
		}
		
		var email = (String) properties.get("email");
		var user = this.getUserByEmail(email);
		
		try {
			PatchUtil.update(user, properties);
		} catch (RESTException exception) {
			throw new UpdateException(User.class, exception);
		}
		
		return userRepository.save(user);
	}
	
	public void deleteUserByEmail(String email) {
		this.getUserByEmail(email);
		userRepository.deleteByEmail(email);
	}
	
}
