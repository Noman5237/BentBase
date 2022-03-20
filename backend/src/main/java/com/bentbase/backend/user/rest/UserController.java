package com.bentbase.backend.user.rest;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllUsers(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (defaultValue = "email,asc") String[] sorts) {
		
		Page<User> usersPage = userService.getAllUsers(page, size, sorts);
		
		var meta = new HashMap<String, Object>();
		meta.put("page", PageUtil.getMeta(usersPage));
		
		Map<String, Object> response = new HashMap<>();
		response.put("meta", meta);
		response.put("data", usersPage.getContent());
		
		return response;
	}
	
	@GetMapping ("/{email}")
	public User getUserByEmail(@PathVariable ("email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@PostMapping ("/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PatchMapping ("/update")
	public User updateUser(@RequestBody Map<String, Object> properties) {
		return userService.updateUser(properties);
	}
	
	@DeleteMapping ("/{email}")
	public String deleteUserByEmail(@PathVariable ("email") String email) {
		userService.deleteUserByEmail(email);
		return String.format("user with email: `%s` is deleted", email);
	}
}