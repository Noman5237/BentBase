package com.bentbase.backend.buyer;

import com.bentbase.backend.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping ("/buyers")
public class BuyerController {
	
	private final BuyerService buyerService;
	
	public BuyerController(BuyerService buyerService) {
		this.buyerService = buyerService;
	}
	
	@GetMapping ()
	public Map<String, Object> getAllBuyers(
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size,
			@RequestParam (required = false, defaultValue = "email,asc") String[] sorts) {
		
		Page<Buyer> buyersPage = buyerService.getAllBuyers(new PageUtil.Paginate(page, size, sorts));
		
		return PageUtil.createResponseWithPaginatedMeta(buyersPage);
	}
	
	@GetMapping ("/{email}")
	public Buyer getBuyerByEmail(@PathVariable ("email") String email) {
		return buyerService.getBuyerByEmail(email);
	}
	
	@PostMapping ("/create")
	public Buyer createBuyer(@RequestBody Buyer buyer) {
		return buyerService.createBuyer(buyer);
	}
	
	@PatchMapping ("/update")
	public Buyer updateBuyer(@RequestBody Map<String, Object> properties) {
		return buyerService.updateBuyer(properties);
	}
	
	@DeleteMapping ("/{email}")
	public String deleteBuyerByEmail(@PathVariable ("email") String email) {
		buyerService.deleteBuyerByEmail(email);
		return String.format("buyer with email: `%s` is deleted", email);
	}
}
