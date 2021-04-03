package com.mobilec.springcloud;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mobilec.springcloud.product.entity.Category;
import com.mobilec.springcloud.product.entity.Product;
import com.mobilec.springcloud.product.repository.ProductRepository;


@DataJpaTest
public class ProductRepositoryMockTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void whenFindByCategory_thenReturnProductList() {
		
		Product product01 = Product.builder()
				.name("computer")
				.category(Category.builder().id(1L).build())
				.description("")
				.stock(new Double("10"))
				.price(new Double("5"))
				.status("created")
				.createdAt(new Date())
				.build();
		
		productRepository.save(product01);
		
		List<Product> founds = productRepository.findByCategory(product01.getCategory());
		
		Assertions.assertThat(founds.size()).isEqualTo(2);
		
	}
	
}
