package com.mobilec.springcloud.customer.service;

import java.util.List;

import com.mobilec.springcloud.customer.entity.Customer;
import com.mobilec.springcloud.customer.entity.Region;

public interface CustomerService {

    public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public  Customer getCustomer(Long id);



}
