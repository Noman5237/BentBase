package com.bentbase.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {
	
	public static Map<String, Object> getMeta(Page<?> page) {
		Map<String, Object> pageMeta = new HashMap<>();
		
		pageMeta.put("totalPages", page.getTotalPages());
		pageMeta.put("totalItems", page.getTotalElements());
		pageMeta.put("size", page.getSize());
		pageMeta.put("page", page.getNumber());
		pageMeta.put("items", page.getNumberOfElements());
		
		return pageMeta;
	}
	
	@AllArgsConstructor
	@Builder
	@Getter
	public static class Paginate {
		
		private int page;
		private int size;
		private String[] sorts;
	}
}
