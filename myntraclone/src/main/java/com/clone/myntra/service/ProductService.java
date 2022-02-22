package com.clone.myntra.service;

import java.util.List;

import com.clone.myntra.repository.entity.Product;

public interface ProductService {
	
	List<Product> getProducts();

	Product addProduct(Product product);

	Product udpateProduct(Product product, Long id);

}