package com.bentbase.backend.seller;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/sellers")
public class SellerController {
	
	private final SellerService sellerService;
	
	public SellerController(SellerService sellerService) {
		this.sellerService = sellerService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllSellers(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "email,asc") String[] sorts) {
		
		Page<Seller> sellersPage = sellerService.getAllSellers(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(sellersPage);
	}
	
	@GetMapping ("/{email}")
	public Seller getSellerByEmail(@PathVariable ("email") String email) {
		return sellerService.getSellerByEmail(email);
	}
	
	@PostMapping ("/create")
	public Seller createSeller(@RequestBody Seller seller) {
		return sellerService.createSeller(seller);
	}
	
	@PatchMapping ("/update")
	public Seller updateSeller(@RequestBody Map<String, Object> properties) {
		return sellerService.updateSeller(properties);
	}
	
	@DeleteMapping ("/{email}")
	public String deleteSellerByEmail(@PathVariable ("email") String email) {
		sellerService.deleteSellerByEmail(email);
		return String.format("seller with email: `%s` is deleted", email);
	}
}
