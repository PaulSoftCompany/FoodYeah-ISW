package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.entities.ProductCategory;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.ProductCategoryRepository;
import com.paulsoft.foodyeah.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ProductCategoryDto getProductCategoryById(Long id) throws ResourceException {
        return convertToResource(productCategoryRepository.findById(id).
                orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public List<ProductCategoryDto> getProductCategories() throws ResourceException {
        return  productCategoryRepository.findAll()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductCategoryDto updateProductCategory(UpdateProductCategoryDto updateProductCategoryDto, Long id) throws ResourceException {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("NOT_FOUND","NOT_FOUND"));
        productCategory.setName(updateProductCategoryDto.getName());
        productCategory.setDescription(updateProductCategoryDto.getDescription());
        return convertToResource(productCategoryRepository.save(productCategory));
    }

    @Override
    @Transactional
    public ProductCategoryDto createProductCategory(CreateProductCategoryDto createProductCategoryDto) throws ResourceException,ParseException {
        ProductCategory productCategory = convertToEntity(createProductCategoryDto);
        if(productCategoryRepository.findByName(productCategory.getName()).isPresent()){
            throw new NotFoundException("PRODUCT_CATEGORY_EXISTS","PRODUCT_CATEGORY_EXISTS");
        }
        productCategory.setState(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(formatter.format(new Date()));
        productCategory.setCreatedAt(date);
        return convertToResource(productCategoryRepository.save(productCategory));
    }

    @Override
    @Transactional
    public String deleteProductCategory(Long id) throws ResourceException {
        ProductCategory productCategory = productCategoryRepository.findById(id).
                orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND"));
        productCategory.setState(false);
        productCategoryRepository.save(productCategory);
        return productCategory.getId().toString();
    }

    private ProductCategory convertToEntity(CreateProductCategoryDto resource){
        return modelMapper.map(resource,ProductCategory.class);
    }

    private ProductCategoryDto convertToResource(ProductCategory entity){
        return modelMapper.map(entity,ProductCategoryDto.class);
    }
}