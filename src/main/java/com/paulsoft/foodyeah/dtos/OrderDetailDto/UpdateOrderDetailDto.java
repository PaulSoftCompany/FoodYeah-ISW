package com.paulsoft.foodyeah.dtos.OrderDetailDto;

import com.paulsoft.foodyeah.entities.Order;
import com.paulsoft.foodyeah.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderDetailDto {

    private Boolean state;
    private Order order;
    private Product product;
    private Byte quantity;

}
