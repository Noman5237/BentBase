package com.bentbase.backend.user;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping ()
	public String helloFromUsers() {
		return "helloFromUsers";
	}
	
	@GetMapping ("/:email")
	public User getUserByEmail(@Param ("email") String email) {
		return userService.getUserByEmail(email);
	}
	
}
