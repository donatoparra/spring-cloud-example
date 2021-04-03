package com.mobilec.springcloud.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mobilec.springcloud.product.entity.Category;
import com.mobilec.springcloud.product.entity.Product;
import com.mobilec.springcloud.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	//@Autowired
	private final ProductRepository pr;
	
	@Override
	public List<Product> listAllProduct() {
		return pr.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return pr.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setStatus("CREATED");
		product.setCreatedAt(new Date());
		return pr.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		
		Product p = getProduct(product.getId());
		
		if (null == p) {
			return null;
		}
		
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setCategory(product.getCategory());
		
		return pr.save(p);
	}

	@Override
	public Product deleteProduct(Long id) {
		
		Product p = getProduct(id);
		
		if (null == p) {
			return null;
		}
		
		p.setStatus("DELETED");
		
		return pr.save(p);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return pr.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		
		Product p = getProduct(id);
		
		if (null == p) {
			return null;
		}
		
		Double stock = p.getStock() + quantity;
		p.setStock(stock);
		
		return pr.save(p);
	}

}
