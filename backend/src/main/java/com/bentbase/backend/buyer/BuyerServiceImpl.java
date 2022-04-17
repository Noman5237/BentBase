package com.bentbase.backend.buyer;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.project.Project;
import com.bentbase.backend.user.User;
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

import static com.bentbase.backend.utils.PageUtil.Paginate;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
	
	private final BuyerRepository buyerRepository;
	
	public BuyerServiceImpl(BuyerRepository buyerRepository) {
		this.buyerRepository = buyerRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Buyer> getAllBuyers(Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(), paginate.getSize(), SortUtil.getOrdersFromStringArray(paginate.getSorts(), User.class));
			return buyerRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Buyer.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Buyer getBuyerByEmail(String email) {
		Optional<Buyer> buyer = buyerRepository.findByEmail(email);
		if (buyer.isEmpty()) {
			throw new GetException(Buyer.class).withError("email", "does not exist");
		}
		
		return buyer.get();
	}
	
	@Override
	public Page<Project> getProjects(Buyer buyer, Paginate paginate) {
		return null;
	}
	
	@SneakyThrows
	@Override
	public Buyer createBuyer(Buyer buyer) {
		Optional<Buyer> existingUser = buyerRepository.findByEmail(buyer.getEmail());
		if (existingUser.isPresent()) {
			throw new CreateException(Buyer.class).withError("email", "already exists");
		}
		
		try {
			return buyerRepository.save(buyer);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Buyer.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Buyer updateBuyer(Map<String, Object> properties) {
		if (!properties.containsKey("email")) {
			throw new GetException(Buyer.class).withError("email", "must not be blank");
		}
		
		var email = (String) properties.get("email");
		var buyer = this.getBuyerByEmail(email);
		
		try {
			PatchUtil.update(buyer, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Buyer.class, exception);
		}
		
		return buyerRepository.save(buyer);
	}
	
	@Override
	public void deleteBuyerByEmail(String email) {
		this.getBuyerByEmail(email);
		buyerRepository.deleteByEmail(email);
	}
}
