package com.paulsoft.foodyeah.dtos.ProductDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String name;
    private Double unitPrice;
    private byte sellDay;
    private String imageUrl;
    //private Long productCategoryId;
}
