package com.paulsoft.foodyeah.dtos.OrderDetailDto;

import com.paulsoft.foodyeah.entities.Order;
import com.paulsoft.foodyeah.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDetailDto {
    private Byte quantity;
}
