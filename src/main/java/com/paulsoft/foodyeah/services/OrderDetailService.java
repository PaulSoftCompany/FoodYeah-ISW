package com.paulsoft.foodyeah.services;

import com.paulsoft.foodyeah.dtos.OrderDetailDto.CreateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.UpdateOrderDetailDto;
import com.paulsoft.foodyeah.entities.OrderDetail;
import com.paulsoft.foodyeah.exceptions.ResourceException;

import java.text.ParseException;
import java.util.List;

public interface OrderDetailService {
    OrderDetailDto getOrderDetailById(Long id) throws ResourceException;
    List<OrderDetailDto> getOrderDetails() throws ResourceException;
    //OrderDetailDto createOrderDetailDto(CreateOrderDetailDto createOrderDetailDto, Long orderId, Long productId) throws ResourceException, ParseException;
    OrderDetailDto updateOrderDetailDto(UpdateOrderDetailDto updateOrderDetailDto, Long id) throws ResourceException;
    //String deleteOrderDetailDto(Long id) throws ResourceException;
}
