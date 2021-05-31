package com.paulsoft.foodyeah.controllers;

import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.exceptions.responses.ResourceResponse;
import com.paulsoft.foodyeah.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/product_categories")
public class ProductCategoryController {
    @Autowired
    ProductCategoryService productCategoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResourceResponse<List<ProductCategoryDto>> getProductCategories() throws ResourceException {
        return new ResourceResponse<>("Success", String.valueOf(HttpStatus.OK),"OK"
                    ,productCategoryService.getProductCategories());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResourceResponse<ProductCategoryDto> getProductCategoryById(@PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productCategoryService.getProductCategoryById(id));
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public ResourceResponse<ProductCategoryDto> createProductCategory(@RequestBody @Valid CreateProductCategoryDto createProductCategoryDto) throws ResourceException, ParseException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productCategoryService.createProductCategory(createProductCategoryDto));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResourceResponse<ProductCategoryDto> updateProductCategory(@RequestBody @Valid UpdateProductCategoryDto updateProductCategoryDto,@PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                productCategoryService.updateProductCategory(updateProductCategoryDto,id));
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResourceResponse<String> deleteProductCategory(@PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                productCategoryService.deleteProductCategory(id));
    }
}
