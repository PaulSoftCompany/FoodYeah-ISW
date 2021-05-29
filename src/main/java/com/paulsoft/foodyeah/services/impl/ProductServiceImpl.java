package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.*;
import com.paulsoft.foodyeah.entities.Customer;
import com.paulsoft.foodyeah.entities.Product;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.ProductRepository;
import com.paulsoft.foodyeah.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public static final ModelMapper modelMapper=new ModelMapper();


    @Override
    public List<ProductDto> getProducts() throws ResourceException {
        return productRepository.findAll()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByState(Boolean state) throws ResourceException {
        return productRepository.findAllByState(state)
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsBySellDay(byte sellDay) throws ResourceException {
        return productRepository.findAllBySellDay(sellDay)
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) throws ResourceException {
        return convertToResource(productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public ProductDto getProductByName(String name) throws ResourceException {
        return convertToResource(productRepository.findByName(name)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    @Transactional
    public ProductDto createProduct(CreateProductDto createProductDto) throws ResourceException, ParseException {
        Product product = convertToEntity(createProductDto);
        if(productRepository.findByName(createProductDto.getName()).isPresent()){
            throw new NotFoundException("PRODUCT_EXISTS","PRODUCT_EXISTS");
        }
        product.setState(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(formatter.format(new Date()));
        product.setCreatedAt(date);
        return convertToResource(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(UpdateProductDto updateProductDto, Long id) throws ResourceException {
        return null;
        //TODO
    }

    @Override
    @Transactional
    public String deleteProduct(Long id) throws ResourceException {
        return null;
        //TODO
    }


    private Product convertToEntity(CreateProductDto resource){return  modelMapper.map(resource, Product.class);}

    private ProductDto convertToResource(Product entity){return  modelMapper.map(entity,ProductDto.class);}
}
