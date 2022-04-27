package com.bentbase.backend.user;

import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
	
	Page<User> getAllUsers(Paginate paginate);
	
	User getUserByEmail(String email);
	
	Page<User> filterUsers(User filter, Paginate paginate);
	
	User updateUser(Map<String, Object> properties);
	
	void deleteUserByEmail(String email);
}
