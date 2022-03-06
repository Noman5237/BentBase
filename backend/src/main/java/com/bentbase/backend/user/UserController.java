package com.bentbase.backend.user;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping ()
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping ("/{email}")
	public User getUserByEmail(@PathVariable ("email") String email) {
		System.out.println(email);
		var user = userService.getUserByEmail(email);
		System.out.println(user);
		return user;
	}
	
}
