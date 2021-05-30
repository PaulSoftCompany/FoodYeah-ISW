package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto;
import com.paulsoft.foodyeah.dtos.orderDto.CreateOrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.OrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.UpdateOrderDto;
import com.paulsoft.foodyeah.entities.*;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CardRepository;
import com.paulsoft.foodyeah.repositories.OrderRepository;
import com.paulsoft.foodyeah.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CardRepository cardRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<OrderDto> getOrders() throws ResourceException {
        return orderRepository.findAll()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) throws ResourceException {
        return convertToResource(orderRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) throws ResourceException, ParseException {
        Order order = convertToEntity(createOrderDto);
        order.setTotalPrice(0.0);
        order.setDate(new Date());
        //TODO: Revisar si el tiempo esta bien asigando
        return convertToResource(orderRepository.save(order));

    }

    /*
    @Override
    public void SetEndTime(UpdateOrderDto order) throws ResourceException {
        //TODO:Ya no hay end time
    }

    @Override
    public void DecreaseStock(OrderDto orderDto) throws ResourceException {
        //TODO: Ya no hay stok en product
    }

    @Override
    public String GetAverageTime() throws ResourceException {
        //TODO:Ya no hya end time por lo tanto ya no se puede calcular el tiempo promedio
    }

    @Override
    public boolean DecreaseCostumerMoney(long cardId, long orderId) throws ResourceException {
    }*/

    private float OrderTotalPrice(Order order) {
        float TotalPrice = 0;
        List<OrderDetail> _orderDetails = order.getProducts();
        for (OrderDetail orderDetail : _orderDetails) {
            TotalPrice += orderDetail.getTotalPrice();
        }
        return TotalPrice;
    }

    private Order convertToEntity(CreateOrderDto resource){return  modelMapper.map(resource, Order.class);}

    private OrderDto convertToResource(Order entity){return  modelMapper.map(entity,OrderDto.class);}
}
