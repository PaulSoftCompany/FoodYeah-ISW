package com.example.controller;

import com.example.entity.ProductCategory;
import com.example.service.ProductCategoryService;
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
@RequestMapping("/product_categories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategory>> listAllProductCategories(@RequestParam(name = "productCategoryId", required = false) Long productCategoryId){
        List<ProductCategory> productCategories = new ArrayList<>();
        if(null == productCategoryId) {
            productCategories = productCategoryService.findProduct_CategoryAll();
            if(productCategories.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(productCategories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> getProductCategory(@PathVariable("id")long id){
        log.info("Fetching ProductCategory with Id {}", id);
        ProductCategory productCategory = productCategoryService.getProduct_Category(id);
        if(null == productCategory){
            log.error("ProductCategory with id {} not found.",id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productCategory);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> createProductCategory(@Valid @RequestBody ProductCategory productCategory, BindingResult result){
        log.info("Creating ProductCategory : {}", productCategory);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        ProductCategory productCategoryDB = productCategoryService.createProduct_Category(productCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable("id") long id, @RequestBody ProductCategory productCategory){
        log.info("Updating ProductCategory with id {}",id);
        ProductCategory currentProductCategory = productCategoryService.getProduct_Category(id);
        if(null == currentProductCategory){
            log.error("Unable to update ProductCategory with id {} not founded",id);
            return ResponseEntity.notFound().build();
        }
        productCategory.setId(id);
        currentProductCategory = productCategoryService.updateProduct_Category(productCategory);
        return ResponseEntity.ok(currentProductCategory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable("id")long id){
        log.info("Fetching & Deleting ProductCategory with id {}", id);
        ProductCategory productCategory = productCategoryService.getProduct_Category(id);
        if(null == productCategory){
            log.error("Unable to delete. ProductCategory with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        productCategory = productCategoryService.deleteProduct_Category(id);
        return ResponseEntity.ok(productCategory);
    }
}