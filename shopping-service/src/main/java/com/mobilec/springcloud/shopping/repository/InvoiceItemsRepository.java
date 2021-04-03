package com.mobilec.springcloud.shopping.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilec.springcloud.shopping.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
	
}
