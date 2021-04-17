package com.example.service;

import com.example.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrderAll();
    Order getOrder(Long id);
    Order createOrder(Order order);
    void SetEndTime(Order order);
    void DecreaseStock(Order order);
    String GetAverageTime();
    boolean DecreaseCostumerMoney(long cardId, long orderId);
}
