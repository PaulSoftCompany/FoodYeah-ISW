package com.paulsoft.foodyeah.controllers;

import com.paulsoft.foodyeah.dtos.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CreateProductDto;
import com.paulsoft.foodyeah.dtos.CustomerDto;
import com.paulsoft.foodyeah.dtos.ProductDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.exceptions.responses.ResourceResponse;
import com.paulsoft.foodyeah.services.OrderService;
import com.paulsoft.foodyeah.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public ResourceResponse<ProductDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto ) throws ResourceException, ParseException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.createProduct(createProductDto));
    }
}
