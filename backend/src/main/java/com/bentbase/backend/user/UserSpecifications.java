package com.bentbase.backend.user;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static com.bentbase.backend.utils.DatabaseUtil.*;

public interface UserSpecifications {
	
	static Specification<User> filterBy(User filter) {
		
		return (Root<User> user, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			List<Order> orders = new ArrayList<>();
			
			if (filter.getFirstName() != null && filter.getFirstName()
			                                           .length() > 0) {
				var firstNamePhoneticMatch = cb.equal(soundex(cb, user.get("firstName")),
				                                      soundex(cb, cb.literal(filter.getFirstName())));
				
				var firstNameEditDistance = cb.asc(editDistance(cb,
				                                                user.get("firstName"),
				                                                cb.literal(filter.getFirstName())));
				
				orders.add(firstNameEditDistance);
				predicates.add(firstNamePhoneticMatch);
			}
			
			if (filter.getLastName() != null && filter.getLastName()
			                                          .length() > 0) {
				var lastNamePredicate = cb.equal(soundex(cb, user.get("lastName")),
				                                 soundex(cb, cb.literal(filter.getLastName())));
				
				var lastNameEditDistance = cb.asc(editDistance(cb,
				                                               user.get("lastName"),
				                                               cb.literal(filter.getFirstName())));
				
				orders.add(lastNameEditDistance);
				predicates.add(lastNamePredicate);
			}
			
			query.orderBy(orders);
			return cb.and(predicates.toArray(Predicate[]::new));
		};
	}
}
