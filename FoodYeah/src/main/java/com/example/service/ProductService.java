package com.example.service;

import com.example.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductAll();
    Product getProduct(Long id);
    List<Product> findBySellday(int Sellday);
    List<Product> findByCategoryId(long id);
    List<Product> menuSemanal();
    List<Product> platosALaCarta();
    List<Product> platosALaCartaByCategoryId(long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
}
