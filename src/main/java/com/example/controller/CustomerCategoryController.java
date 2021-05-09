package com.example.controller;

import com.example.entity.CustomerCategory;
import com.example.service.CustomerCategoryService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer_categories")
public class CustomerCategoryController {
    @Autowired
    private CustomerCategoryService customerCategoryService;

    @GetMapping
    public ResponseEntity<List<CustomerCategory>> listAllCustomerCategories(
            @RequestParam(name = "customerCategoryId", required = false) Long customerCategoryId) {
        List<CustomerCategory> customerCategories = new ArrayList<>();
        if (null == customerCategoryId) {
            customerCategories = customerCategoryService.findCustomerCategoryAll();
            if (customerCategories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(customerCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerCategory> getCustomerCategory(@PathVariable("id") long id) {
        log.info("Fetching CustomerCategory with Id {}", id);

        CustomerCategory customerCategory = customerCategoryService.getCustomerCategory(id);

        if (null == customerCategory) {
            log.error("CustomerCategory with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerCategory);
    }

    @PostMapping
    public ResponseEntity<CustomerCategory> createCustomerCategory(
            @Valid @RequestBody CustomerCategory customerCategory, BindingResult result) {
        log.info("Creating CustomerCategory : {}", customerCategory);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        CustomerCategory customerCategoryDB = customerCategoryService.createCustomerCategory(customerCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCategoryDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerCategory> updateCustomerCategory(@PathVariable("id") long id,
            @RequestBody CustomerCategory customerCategory) {
        log.info("Updating CustomerCategory with id {}", id);
        CustomerCategory currentCustomerCategory = customerCategoryService.getCustomerCategory(id);
        if (null == currentCustomerCategory) {
            log.error("Unable to update CustomerCategory with id {} not founded", id);
            return ResponseEntity.notFound().build();
        }
        customerCategory.setId(id);
        currentCustomerCategory = customerCategoryService.updateCustomerCategory(customerCategory);
        return ResponseEntity.ok(currentCustomerCategory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomerCategory> deleteCustomerCategory(@PathVariable("id") long id) {
        log.info("Fetching & Deleting CustomerCategory with id {}", id);
        CustomerCategory customerCategory = customerCategoryService.getCustomerCategory(id);
        if (null == customerCategory) {
            log.error("Unable to delete. CustomerCategory with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        customerCategory = customerCategoryService.deleteCustomerCategory(id);
        return ResponseEntity.ok(customerCategory);
    }
}
