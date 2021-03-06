package com.clone.myntra.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clone.myntra.repository.entity.Product;
import com.clone.myntra.repository.entity.User;
import com.clone.myntra.service.ProductService;
import com.clone.myntra.service.UserService;
import com.clone.myntra.service.model.ProductSearchRequestModel;

@RestController
@RequestMapping("v1/product")
public class ProductApi {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/") 
	public ResponseEntity<?> listProducts() {
		List<Product> products = productService.getProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		Product savedProduct = productService.addProduct(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id){
		Product savedProduct = productService.udpateProduct(product, id);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/pagination/") 
	public ResponseEntity<?> listProductsWithPagination(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize) {
		
		//  create page Request 
		 PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		// call findAll service method
		 Page<Product> page = productService.findAll(pageRequest);
	
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	//Search Idompotent methods
	@GetMapping("/search/") 
	public ResponseEntity<?> searchProducts(@RequestParam Map<String, String> requestMap) {
		
		ProductSearchRequestModel requestModel = new ProductSearchRequestModel(requestMap);
	
		List<Product> list = productService.search(requestModel);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody User user){
		User addedUser = userService.adduser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	//api for which alter the prices based on custom input
	@PutMapping("/alter/{price}")
	public ResponseEntity<?> alterPrices(@PathVariable Integer price){
		
		List<Product> alteredPrice = productService.alterData(price);
		return new ResponseEntity<>(alteredPrice, HttpStatus.OK);
		
		
	}
	
}

