package com.mobilec.springcloud.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilec.springcloud.product.entity.Category;
import com.mobilec.springcloud.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category category);
	
}
