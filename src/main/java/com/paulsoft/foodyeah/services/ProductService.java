package com.paulsoft.foodyeah.services;

import com.paulsoft.foodyeah.dtos.CreateProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto;
import com.paulsoft.foodyeah.dtos.UpdateProductDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;

import java.text.ParseException;
import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts() throws ResourceException;
    List<ProductDto> getProductsByState(Boolean state) throws ResourceException;
    List<ProductDto> getProductsBySellDay(byte sellDay) throws ResourceException;
    ProductDto getProductById(Long id) throws ResourceException;
    ProductDto getProductByName(String name) throws ResourceException;
    ProductDto createProduct(CreateProductDto createProductDto) throws ResourceException, ParseException;
    ProductDto updateProduct(UpdateProductDto updateProductDto, Long id) throws ResourceException;
    String deleteProduct(Long id) throws ResourceException;
}
