package com.example.service;

import java.util.List;

import com.example.entity.Customer;
import com.example.entity.CustomerCategory;

public interface CustomerService {
    List<Customer> findCustomerAll();

    Customer getCustomer(Long id);

    List<Customer> findByCustomerCategory(CustomerCategory category);

    Customer findOneByUsername(String username);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(Long id);

    void assignRole(Long customerId, Long roleId);
}
