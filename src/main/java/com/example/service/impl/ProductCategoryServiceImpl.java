package com.example.service.impl;

import com.example.entity.ProductCategory;
import com.example.repository.ProductCategoryRepository;
import com.example.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findProduct_CategoryAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProduct_Category(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public ProductCategory createProduct_Category(ProductCategory product_category) {
        product_category.setState("CREATED");
        return productCategoryRepository.save(product_category);
    }

    @Override
    public ProductCategory updateProduct_Category(ProductCategory product_category) {
        ProductCategory product_categoryDB = this.getProduct_Category(product_category.getId());
        if (product_categoryDB == null) {
            return null;
        }
        product_categoryDB.setProductCategoryName(product_category.getProductCategoryName());
        product_categoryDB.setProductCategoryDescription(product_category.getProductCategoryDescription());
        product_categoryDB.setState("UPDATED");
        return productCategoryRepository.save(product_categoryDB);

    }

    @Override
    public ProductCategory deleteProduct_Category(Long id) {
        ProductCategory product_categoryDB = this.getProduct_Category(id);
        if (product_categoryDB == null) {
            return null;
        }
        product_categoryDB.setState("DELETED");
        return productCategoryRepository.save(product_categoryDB);
    }
}
