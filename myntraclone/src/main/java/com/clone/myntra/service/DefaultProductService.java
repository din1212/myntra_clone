package com.clone.myntra.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.clone.myntra.repository.ProductRepository;
import com.clone.myntra.repository.entity.Product;

@Service
public class DefaultProductService implements ProductService {

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

		if (!optionalProduct.isPresent()) {

			throw new RuntimeException("The id :"+ id + "is not present");
		}
		Product existingProduct = optionalProduct.get();
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setDes(product.getDes());
		existingProduct.setQty(product.getQty());
		existingProduct.setRating(product.getRating());
		productRepository.save(existingProduct);
		
		return existingProduct;
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);

		if (!optionalProduct.isPresent()) {
			throw new RuntimeException("Product ID is not found " + id);
		}
		productRepository.deleteById(id);
	}

	@Override
	public Page<Product> findAll(PageRequest pageRequest) {
		return productRepository.findAll(pageRequest);
	}

}
