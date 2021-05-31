package com.paulsoft.foodyeah.controllers;

import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductDto.CreateProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.ProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.UpdateProductDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.exceptions.responses.ResourceResponse;
import com.paulsoft.foodyeah.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResourceResponse<List<ProductDto>> getProducts() throws ResourceException {
        return new ResourceResponse<>("Success", String.valueOf(HttpStatus.OK),"OK"
                ,productService.getProducts());
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResourceResponse<ProductDto> getProductById(@PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.getProductById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("?state={state}")
    public ResourceResponse<List<ProductDto>> getProductsByState(@PathVariable("state") Boolean state) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.getProductsByState(state));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("?sellDay={sellDay}")
    public ResourceResponse<List<ProductDto>> getProductsBySellDay(@PathVariable("sellDay") byte sellDay) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.getProductsBySellDay(sellDay));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("category={category_id}")
    public ResourceResponse<List<ProductDto>> getProductsByCategoryId(@PathVariable("category_id") Long category_id) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.getProductsByCategoryId(category_id));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("?name={name}")
    public ResourceResponse<ProductDto> getProductByName(@PathVariable("name") String name) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.getProductByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public ResourceResponse<ProductDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto ) throws ResourceException, ParseException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.createProduct(createProductDto));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResourceResponse<ProductDto> updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto, @PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                productService.updateProduct(updateProductDto,id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResourceResponse<String> deleteProduct(@PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productService.deleteProduct(id));
    }

}
