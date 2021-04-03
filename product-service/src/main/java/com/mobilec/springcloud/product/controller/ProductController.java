package com.mobilec.springcloud.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilec.springcloud.product.entity.Category;
import com.mobilec.springcloud.product.entity.Product;
import com.mobilec.springcloud.product.service.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService ps;
	
	@GetMapping
	public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId", required=false) Long categoryId) {
		
		List<Product> products = new ArrayList<>();
		
		if (null == categoryId) {
			
			products = ps.listAllProduct();
			
			if (products.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			
		} else {
			products = ps.findByCategory(Category.builder().id(categoryId).build());
			
			if (products.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
		}
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		
		Product product = ps.getProduct(id);
		
		if (null == product) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product);
		
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
		
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		
		Product newProduct = ps.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		
		product.setId(id);
		
		Product productDB = ps.updateProduct(product);
		
		if (null == productDB) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productDB);
		
	} 
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
		
		Product productDeleted = ps.deleteProduct(id);
		
		if (null == productDeleted) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productDeleted);
		
	} 
	
	@GetMapping(value="/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id, @RequestParam(name="quantity", required=true) Double quantity) {
		
		Product product = ps.updateStock(id, quantity);
		
		if (null == product) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product);
		
	} 
	
	
	
	private String formatMessage(BindingResult result) {
		
		List<Map<String, String>> errors = result.getFieldErrors().stream()
				.map( err -> {
					Map<String, String> m = new HashMap<>();
					m.put(err.getField(), err.getDefaultMessage());
					return m;
				}).collect(Collectors.toList());
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("01")
				.messages(errors).build();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "";
		try {
			json = mapper.writeValueAsString(errorMessage);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
		
		return json;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
