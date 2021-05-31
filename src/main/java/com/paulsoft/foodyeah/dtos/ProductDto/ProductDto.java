package com.paulsoft.foodyeah.dtos.ProductDto;

import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.entities.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Double productPrice;
    private Byte sellDay;
    private String imageUrl;
    private Boolean state;
    private Date createdAt;
    private Long productCategoryId;

}
