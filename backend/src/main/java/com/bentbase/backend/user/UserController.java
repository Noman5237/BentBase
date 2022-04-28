package com.bentbase.backend.user;

import com.bentbase.backend.review.Review;
import com.bentbase.backend.utils.PageUtil;
import com.bentbase.backend.utils.PageUtil.Paginate;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllUsers(@RequestParam (defaultValue = "0") int page,
	                                       @RequestParam (defaultValue = "10") int size,
	                                       @RequestParam (required = false, defaultValue = "email,asc") String[] sorts) {
		
		Page<User> usersPage = userService.getAllUsers(new Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(usersPage);
	}
	
	@GetMapping ("/{email}")
	public User getUserByEmail(@PathVariable ("email") String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping ("/{email}/providedReviews")
	public Map<String, Object> getAllProvidedReviews(@PathVariable ("email") String email,
	                                                 @RequestParam (defaultValue = "0") int page,
	                                                 @RequestParam (defaultValue = "10") int size,
	                                                 @RequestParam (required = false, defaultValue = "rating,desc") String[] sorts) {
		
		Page<Review> reviewPage = userService.getProvidedReviews(email, new Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(reviewPage);
	}
	
	@GetMapping ("/{email}/receivedReviews")
	public Map<String, Object> getAllReceivedReviews(@PathVariable ("email") String email,
	                                                 @RequestParam (defaultValue = "0") int page,
	                                                 @RequestParam (defaultValue = "10") int size,
	                                                 @RequestParam (required = false, defaultValue = "rating,desc") String[] sorts) {
		
		Page<Review> reviewPage = userService.getReceivedReviews(email, new Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(reviewPage);
	}
	
	@PostMapping ("/filter")
	public Map<String, Object> filterUsers(@RequestBody User filter,
	                                       @RequestParam (defaultValue = "0") int page,
	                                       @RequestParam (defaultValue = "10") int size) {
		
		Page<User> usersPage = userService.filterUsers(filter, new Paginate(page, size, null));
		
		return PageUtil.createResponseWithPaginatedMeta(usersPage);
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