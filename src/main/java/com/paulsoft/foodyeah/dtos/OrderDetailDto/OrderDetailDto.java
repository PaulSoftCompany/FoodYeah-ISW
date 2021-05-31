package com.paulsoft.foodyeah.dtos.OrderDetailDto;

import com.paulsoft.foodyeah.dtos.orderDto.OrderDto;
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
    private String unitName;
}
