package com.paulsoft.foodyeah.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Byte quantity;

    @Column(name ="unit_price")
    private Double unitPrice;

    @Column(name ="total_price")
    private Double totalPrice;

    private Boolean state;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
