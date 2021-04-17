package com.example.service;

import com.example.entity.Customer;
import com.example.entity.CustomerCategory;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService{
    List<Customer> findCustomerAll();
    Customer getCustomer(Long id);

    List<Customer> findByCustomerCategory(CustomerCategory category);
    Customer findOneByUsername(String username);

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteCustomer(Long id);

    void assignRole(Long customerId, Long roleId);
}
