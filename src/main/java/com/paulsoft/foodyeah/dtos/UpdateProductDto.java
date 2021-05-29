package com.paulsoft.foodyeah.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private String name;
    private Double productPrice;
    private byte sellDay;
    private String imageUrl;
    private String[] ingredients;
    private Long productCategoryId;
}