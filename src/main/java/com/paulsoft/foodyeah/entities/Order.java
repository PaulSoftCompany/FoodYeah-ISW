package com.paulsoft.foodyeah.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Column(name ="total_price")
    private Double totalPrice;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderDetail> products;


}