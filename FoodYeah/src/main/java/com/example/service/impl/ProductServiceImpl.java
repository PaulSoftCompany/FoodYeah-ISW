package com.example.service.impl;

import java.util.List;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepository productRepository;

    @Override
    public List<Product> findProductAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findBySellday(int Sellday) {
        return productRepository.findBySellDay(Sellday);
    }

    @Override
    public List<Product> findByCategoryId(long id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<Product> menuSemanal() {
        return productRepository.menuSemanal();
    }

    @Override
    public List<Product> platosALaCartaByCategoryId(long id) {
        return productRepository.platosALaCartaByCategory(id);
    }

    @Override
    public List<Product> platosALaCarta() {
        return productRepository.platosALaCarta();
    }

    @Transactional
    @Override
    public Product createProduct(Product product) {

        product.setState("CREATED");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (productDB == null) {
            return null;
        }
        productDB.setCategory(product.getCategory());
        productDB.setProductName(product.getProductName());
        productDB.setProductPrice(product.getProductPrice());
        productDB.setSellday(product.getSellday());
        productDB.setStock(product.getStock());
        product.setIngredients(product.getIngredients());
        productDB.setState("UPDATED");
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if (productDB == null) {
            return null;
        }
        productDB.setState("DELETE");
        return productRepository.save(productDB);
    }
}
