package com.clone.myntra.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.clone.myntra.repository.entity.Product;
import com.clone.myntra.service.model.ProductSearchRequestModel;

public interface ProductService {
	
	List<Product> getProducts();

	Product addProduct(Product product);

	Product udpateProduct(Product product, Long id);

	void deleteProduct(Long id);

	Page<Product> findAll(PageRequest pageRequest);

	List<Product> search(ProductSearchRequestModel requestModel);

	List<Product> alterData(Integer price);

}
