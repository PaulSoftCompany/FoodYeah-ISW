package com.paulsoft.foodyeah.dtos.orderDto;
import com.paulsoft.foodyeah.entities.OrderDetail;

import java.util.Date;
import java.util.List;

public class CreateOrderDto {
    private Date date;
    private Double totalPrice;
    private List<OrderDetailDto> products;

}
