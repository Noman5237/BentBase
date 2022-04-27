package com.bentbase.backend.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

public class DatabaseUtil {
	
	public static Expression<String> soundex(CriteriaBuilder cb, Expression<?> text) {
		return cb.function("soundex", String.class, text);
	}
	
	public static Expression<Long> editDistance(CriteriaBuilder cb, Expression<?> text1, Expression<?> text2) {
		return cb.function("utl_match.edit_distance", Long.class, text1, text2);
	}
}
