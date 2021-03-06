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
	
	public static Map<String, Object> createResponseWithPaginatedMeta(Page<?> page) {
		var meta = new HashMap<String, Object>();
		meta.put("page", PageUtil.getMeta(page));
		
		Map<String, Object> response = new HashMap<>();
		response.put("meta", meta);
		response.put("data", page.getContent());
		return response;
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
