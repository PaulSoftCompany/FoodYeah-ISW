package com.paulsoft.foodyeah.dtos;

import com.paulsoft.foodyeah.entities.ProductCategory;

import javax.persistence.Column;
import java.util.Date;

public class ProductDto {
    private Long id;
    private String name;
    private Double productPrice;
    private byte sellDay;
    private String imageUrl;
    private String[] ingredients;
    private Boolean state;
    private Date createdAt;
    //private ProductCategoryDto productCategory;

}
