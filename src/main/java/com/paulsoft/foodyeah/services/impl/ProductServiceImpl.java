package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.ProductDto.CreateProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.ProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.UpdateProductDto;
import com.paulsoft.foodyeah.entities.Product;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.ProductCategoryRepository;
import com.paulsoft.foodyeah.repositories.ProductRepository;
import com.paulsoft.foodyeah.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public static final ModelMapper modelMapper=new ModelMapper();


    @Override
    public List<ProductDto> getProducts() throws ResourceException {
        return convertToResources(productRepository.findAll());
    }

    @Override
    public List<ProductDto> getProductsByState(Boolean state) throws ResourceException {
        return convertToResources(productRepository.findAllByState(state));
    }
    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) throws ResourceException{
        return convertToResources(productRepository.findAllByProductCategoryId(categoryId));
    }

    @Override
    public List<ProductDto> getProductsBySellDay(byte sellDay) throws ResourceException {
        return convertToResources(productRepository.findAllBySellDay(sellDay));
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
        if(productRepository.findByName(createProductDto.getName()).isPresent()){
            throw new NotFoundException("PRODUCT_EXISTS","PRODUCT_EXISTS");
        }
        Product product = new Product();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(formatter.format(new Date()));

        product.setCreatedAt(date);
        product.setProductCategory(productCategoryRepository.findById(createProductDto.getProductCategoryId())
                .orElseThrow(()-> new NotFoundException("NOT_FOUND","NOT_FOUND")));
        product.setName(createProductDto.getName());
        product.setProductPrice(createProductDto.getProductPrice());
        product.setSellDay(createProductDto.getSellDay());
        product.setImageUrl(createProductDto.getImageUrl());
        product.setState(true);
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


    private Product getProductEntity(Long id) throws ResourceException {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND","NOT_FOUND"));
    }
    private List<ProductDto> convertToResources(List<Product> products) {
        return products.stream().map(x -> modelMapper.map(x, ProductDto.class)).collect(Collectors.toList());
    }
    private Product convertToEntity(CreateProductDto resource){return  modelMapper.map(resource, Product.class);}
    private ProductDto convertToResource(Product entity){return  modelMapper.map(entity,ProductDto.class);}
}
