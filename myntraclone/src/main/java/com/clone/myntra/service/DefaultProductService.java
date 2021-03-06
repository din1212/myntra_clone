package com.clone.myntra.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.clone.myntra.repository.ProductRepository;
import com.clone.myntra.repository.entity.Product;
import com.clone.myntra.service.model.ProductSearchRequestModel;
import com.clone.myntra.service.specification.ProductSpecification;

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

			throw new RuntimeException("The record with :" + id + " is not present");
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

			throw new RuntimeException("The record with :" + id + " is not present");
		}
		productRepository.deleteById(id);
	}

	@Override
	public Page<Product> findAll(PageRequest pageRequest) {
		return productRepository.findAll(pageRequest);
	}

	@Override
	public List<Product> search(ProductSearchRequestModel requestModel) {

		ProductSpecification spec = new ProductSpecification(requestModel);

		List<Product> findAll = productRepository.findAll(spec);

		return findAll;
	}

	@Override
	public List<Product> alterData(Integer price) {
		List<Product> products = getProducts();
		
		return products.stream()
		.map(product -> {
			product.setPrice(product.getPrice()*price);
			return product;
			})
		.filter(a -> a.getRating()> 3)
		.collect(Collectors.toList());
	}
}
