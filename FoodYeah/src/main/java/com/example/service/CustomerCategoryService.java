package com.example.service;

import com.example.entity.CustomerCategory;

import java.util.List;

public interface CustomerCategoryService {
    List<CustomerCategory> findCustomerCategoryAll();
    CustomerCategory getCustomerCategory(Long id);

    CustomerCategory createCustomerCategory(CustomerCategory customer_category);
    CustomerCategory updateCustomerCategory(CustomerCategory customer_category);
    CustomerCategory deleteCustomerCategory(Long id);
}
