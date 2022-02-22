package com.clone.myntra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.myntra.repository.ProductRepository;
import com.clone.myntra.repository.entity.Product;

@Service
public class DefaultProductService implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product udpateProduct(Product product, Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		Product existingProduct = optionalProduct.get();
		if (optionalProduct.isPresent()) {
			
			existingProduct.setName(product.getName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setCategory(product.getCategory());
			existingProduct.setDes(product.getDes());
			existingProduct.setQty(product.getQty());
			existingProduct.setRating(product.getRating());	
			productRepository.save(existingProduct);
		}
		return existingProduct;
		
		
		
	}
	
}
