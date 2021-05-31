package com.paulsoft.foodyeah.services;

import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;

import java.text.ParseException;
import java.util.List;

public interface ProductCategoryService{
    ProductCategoryDto getProductCategoryById(Long id) throws ResourceException;
    List<ProductCategoryDto> getProductCategories() throws ResourceException;
    ProductCategoryDto updateProductCategory(UpdateProductCategoryDto updateProductCategoryDto, Long id) throws ResourceException;
    ProductCategoryDto createProductCategory(CreateProductCategoryDto createProductCategoryDto) throws ResourceException, ParseException;
    String deleteProductCategory(Long id) throws ResourceException;
}
