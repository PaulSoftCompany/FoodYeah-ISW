package com.example.service.impl;

import com.example.entity.CustomerCategory;
import com.example.repository.CustomerCategoryRepository;
import com.example.service.CustomerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCategoryServiceImpl implements CustomerCategoryService {
    @Autowired
    private CustomerCategoryRepository customerCategoryRepository;

    @Override
    public List<CustomerCategory> findCustomerCategoryAll() {
        return customerCategoryRepository.findAll();
    }

    @Override
    public CustomerCategory getCustomerCategory(Long id) {
        return customerCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerCategory createCustomerCategory(CustomerCategory customer_category) {

        customer_category.setState("CREATED");
return customerCategoryRepository.save(customer_category);
    }

    @Override
    public CustomerCategory updateCustomerCategory(CustomerCategory customer_category) {
        CustomerCategory customerCategoryDB=this.getCustomerCategory(customer_category.getId());
        if(customerCategoryDB==null){
            return null;
        }
        customerCategoryDB.setCustomerCategoryName(customer_category.getCustomerCategoryName());
        customerCategoryDB.setCustomerCategoryDescription(customer_category.getCustomerCategoryDescription());
        customerCategoryDB.setState("UPDATED");
        return customerCategoryRepository.save(customerCategoryDB);
    }

    @Override
    public CustomerCategory deleteCustomerCategory(Long id) {
        CustomerCategory customerCategoryDB=this.getCustomerCategory(id);
        if(customerCategoryDB==null){
            return null;
        }
        customerCategoryDB.setState("DELETED");
        return customerCategoryRepository.save(customerCategoryDB);
    }
}
