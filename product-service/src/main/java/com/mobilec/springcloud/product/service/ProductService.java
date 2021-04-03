package com.mobilec.springcloud.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mobilec.springcloud.product.entity.Category;
import com.mobilec.springcloud.product.entity.Product;

@Service
public interface ProductService {

	public List<Product> listAllProduct();
	
	public Product getProduct(Long id);
	
	public Product createProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public Product deleteProduct(Long id);
	
	public List<Product> findByCategory(Category category);
	
	public Product updateStock(Long id, Double quantity);
	
	
}
