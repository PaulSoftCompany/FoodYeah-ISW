package com.paulsoft.foodyeah.services;

import com.paulsoft.foodyeah.dtos.orderDto.CreateOrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.OrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.UpdateOrderDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders() throws ResourceException;
    OrderDto getOrderById(Long id) throws ResourceException;
    OrderDto createOrder(CreateOrderDto createOrderDto) throws ResourceException, ParseException;
    /*void SetEndTime(UpdateOrderDto order) throws ResourceException;
    void DecreaseStock(OrderDto orderDto) throws ResourceException;
    String GetAverageTime() throws ResourceException;
    boolean DecreaseCostumerMoney(long cardId, long orderId) throws ResourceException;*/
}
