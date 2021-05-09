package com.example.repository;

import java.util.List;

import com.example.entity.Customer;
import com.example.entity.CustomerCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findOneByUsername(String username);
    List<Customer>  findByCustomerCategory(CustomerCategory customerCategory);
    
    @Modifying
    @Query(value="INSERT INTO customer_roles(customer_id,role_id) VALUES(:customerId,:roleId)",nativeQuery =true)
    void assignRole( Long customerId, Long roleId );
}
