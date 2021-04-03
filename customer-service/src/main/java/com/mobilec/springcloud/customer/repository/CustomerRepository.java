package com.mobilec.springcloud.customer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilec.springcloud.customer.entity.Customer;
import com.mobilec.springcloud.customer.entity.Region;


public interface CustomerRepository  extends JpaRepository<Customer,Long> {
        public Customer findByNumberID(String numberID);
        public List<Customer> findByLastName(String lastName);
        public List<Customer> findByRegion(Region region);
}
