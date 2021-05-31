package com.paulsoft.foodyeah.dtos.orderDto;

import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Date date;
    private Double totalPrice;
    private List<OrderDetailDto> details;
}
