package com.example.service;

import com.example.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findProduct_CategoryAll();
    ProductCategory getProduct_Category(Long id);

    ProductCategory createProduct_Category(ProductCategory product_category);
    ProductCategory updateProduct_Category(ProductCategory product_category);
    ProductCategory deleteProduct_Category(Long id);

}
