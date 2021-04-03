package com.mobilec.springcloud;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobilec.springcloud.product.entity.Category;
import com.mobilec.springcloud.product.entity.Product;
import com.mobilec.springcloud.product.repository.ProductRepository;
import com.mobilec.springcloud.product.service.ProductService;
import com.mobilec.springcloud.product.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {

	@Mock
	private ProductRepository pr;
	
	@Autowired
	private ProductService ps;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		ps = new ProductServiceImpl(pr);
		
		Product computer = Product.builder()
				.id(1L)
				.name("computer")
				.category(Category.builder().id(1L).build())
				.price(new Double("20"))
				.stock(new Double("5"))
				.build();
		
		Mockito.when(pr.findById(1L))
			.thenReturn(Optional.of(computer));
		
		Mockito.when(pr.save(computer))
			.thenReturn(computer);
		
	}
	
	@Test
	public void whenValidGetId_thenReturnProduct() {
		
		Product found = ps.getProduct(1L);
		
		Assertions.assertThat(found.getName()).isEqualTo("computer");
		
	}
	
	@Test
	public void whenValidUpdateStock_thenReturnNewStock() {
		
		Product newStock = ps.updateStock(1L, Double.parseDouble("5"));
		
		Assertions.assertThat(newStock.getStock()).isEqualTo(10);
	}
	
}
