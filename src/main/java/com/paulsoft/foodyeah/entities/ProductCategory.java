package com.paulsoft.foodyeah.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",unique=true,nullable=false)
    private Long id;

    private String name;

    private String description;

    private Date createdAt;

    private Boolean state;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "productCategory")
//    private List<Product> products;
}