package com.paulsoft.foodyeah.dtos.ProductCategoryDto;

import com.paulsoft.foodyeah.dtos.ProductDto.ProductDto;
import com.paulsoft.foodyeah.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Setter
@Getter
public class ProductCategoryDto {
    private Long id;

    private String name;

    private String description;

    private Date createdAt;

    private Boolean state;

    private List<ProductDto> products;
}
