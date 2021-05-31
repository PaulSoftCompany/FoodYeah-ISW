package com.paulsoft.foodyeah.dtos.orderDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.CreateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import com.paulsoft.foodyeah.entities.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateOrderDto {
    private Date date;
    private List<CreateOrderDetailDto> details;
}
