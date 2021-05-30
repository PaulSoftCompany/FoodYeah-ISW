package com.paulsoft.foodyeah.dtos.orderDto;

import com.paulsoft.foodyeah.entities.OrderDetail;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private Date date;
    private Double totalPrice;
    private List<OrderDetail> products;
}
