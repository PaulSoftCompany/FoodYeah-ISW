package com.paulsoft.foodyeah.dtos.OrderDetailDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulsoft.foodyeah.entities.Order;
import com.paulsoft.foodyeah.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDetailDto {
    private Long id;
    private Byte quantity;
    private Double unitPrice;
    private Double totalPrice;
    private Boolean state;
    private Date createdAt;

    private Long orderId;
    private Long productId;
}
