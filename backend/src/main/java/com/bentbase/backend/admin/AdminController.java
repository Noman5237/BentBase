package com.bentbase.backend.admin;

import com.bentbase.backend.admin.Admin;
import com.bentbase.backend.admin.AdminService;
import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/admins")
public class AdminController {
	
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllAdmins(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "email,asc") String[] sorts) {
		
		Page<Admin> adminsPage = adminService.getAllAdmins(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(adminsPage);
	}
	
	@GetMapping ("/{email}")
	public Admin getAdminByEmail(@PathVariable ("email") String email) {
		return adminService.getAdminByEmail(email);
	}
	
	@PostMapping ("/create")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminService.createAdmin(admin);
	}
	
	@PatchMapping ("/update")
	public Admin updateAdmin(@RequestBody Map<String, Object> properties) {
		return adminService.updateAdmin(properties);
	}
	
	@DeleteMapping ("/{email}")
	public String deleteAdminByEmail(@PathVariable ("email") String email) {
		adminService.deleteAdminByEmail(email);
		return String.format("admin with email: `%s` is deleted", email);
	}
}
