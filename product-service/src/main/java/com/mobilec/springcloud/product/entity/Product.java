package com.mobilec.springcloud.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Nombre no puede ser vacio")
	private String name;
	
	private String description;
	
	@Positive(message="Stock debe ser mayor a cero")
	private Double stock;
	
	private Double price;
	
	private String status;

	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@NotNull(message="La categoria no puede estar vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category;
	
}
