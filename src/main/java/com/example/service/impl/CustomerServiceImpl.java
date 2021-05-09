package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Customer;
import com.example.entity.CustomerCategory;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("foo", "foo", new ArrayList<>());
    }

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findByCustomerCategory(CustomerCategory category) {
        return customerRepository.findByCustomerCategory(category);
    }

    @Override
    public Customer findOneByUsername(String username) {
        return customerRepository.findOneByUsername(username);
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        Long UserRole;
        UserRole = 1L;

        customer.setState("CREATED");
        customer.setPassword(customer.getPassword());
        Customer guardar = customerRepository.save(customer);
        customerRepository.assignRole(guardar.getId(), UserRole);
        return guardar;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = this.getCustomer(customer.getId());
        if (customerDB == null) {
            return null;
        }
        customerDB.setCustomerAge(customer.getCustomerAge());
        customerDB.setCustomerName(customer.getCustomerName());
        customerDB.setState("UPDATED");
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customerDB = this.getCustomer(id);
        if (customerDB == null) {
            return null;
        }
        customerDB.setState("DELETED");
        return customerRepository.save(customerDB);
    }

    @Override
    @Transactional
    public void assignRole(Long customerId, Long roleId) {
        customerRepository.assignRole(customerId, roleId);
    }
}
