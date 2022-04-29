package com.bentbase.backend.review;

import com.bentbase.backend.core.exception.RESTException;
import com.bentbase.backend.core.exception.generic.CreateException;
import com.bentbase.backend.core.exception.generic.GetException;
import com.bentbase.backend.core.exception.generic.UpdateException;
import com.bentbase.backend.user.UserServiceImpl;
import com.bentbase.backend.utils.PageUtil;
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

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final ReviewRepository reviewRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@SneakyThrows
	@Override
	public Page<Review> getAllReviews(PageUtil.Paginate paginate) {
		try {
			PageRequest pagingSort = PageRequest.of(paginate.getPage(),
			                                        paginate.getSize(),
			                                        SortUtil.getOrdersFromStringArray(paginate.getSorts(),
			                                                                          Review.class));
			return reviewRepository.findAll(pagingSort);
		} catch (RESTException exception) {
			throw new GetException(Review.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Review getReviewById(Long id) {
		Optional<Review> review = reviewRepository.findById(id);
		if (review.isEmpty()) {
			throw new GetException(Review.class).withError("id", "does not exist");
		}
		
		return review.get();
	}
	
	@SneakyThrows
	@Override
	public Review createReview(Review review) {
		try {
			return reviewRepository.save(review);
		} catch (TransactionSystemException | JpaSystemException exception) {
			throw new CreateException(Review.class, exception);
		}
	}
	
	@SneakyThrows
	@Override
	public Review updateReview(Map<String, Object> properties) {
		if (!properties.containsKey("id")) {
			throw new GetException(Review.class).withError("id", "must not be blank");
		}
		
		var id = Long.valueOf((Integer) properties.get("id"));
		var review = this.getReviewById(id);
		
		if (properties.containsKey("reviewer")) {
			var reviewer = String.valueOf(properties.get("reviewer"));
			if (!review.getReviewer()
			           .equals(reviewer)) {
				throw new GetException(Review.class).withError("reviewer", "reviewer cannot be updated");
			}
		}
		
		try {
			PatchUtil.update(review, properties);
		} catch (RESTException exception) {
			throw new UpdateException(Review.class, exception);
		}
		
		return reviewRepository.save(review);
	}
	
	@Override
	public void deleteReviewById(Long id) {
		this.getReviewById(id);
		reviewRepository.deleteById(id);
	}
}
