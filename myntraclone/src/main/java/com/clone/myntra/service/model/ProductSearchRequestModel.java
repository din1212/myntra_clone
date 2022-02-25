package com.clone.myntra.service.model;

import java.util.Map;

public class ProductSearchRequestModel {

	//private final String name;
	//private final String des;
	//private final Double price;
	//private final Integer qty;
	private final Integer rating;
	//private final String category;
	
	public ProductSearchRequestModel(Map<String,String> params) {
		this.rating = Integer.valueOf(params.get("rating"));	
	}

	public Integer getRating() {
		return rating;
	}

	public boolean isNotNull() {
		return rating !=null;
	}
}
