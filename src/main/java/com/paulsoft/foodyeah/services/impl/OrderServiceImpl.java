package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.OrderDetailDto.CreateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import com.paulsoft.foodyeah.dtos.orderDto.CreateOrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.OrderDto;
import com.paulsoft.foodyeah.entities.*;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CardRepository;
import com.paulsoft.foodyeah.repositories.OrderDetailRepository;
import com.paulsoft.foodyeah.repositories.OrderRepository;
import com.paulsoft.foodyeah.repositories.ProductRepository;
import com.paulsoft.foodyeah.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ProductRepository productRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<OrderDto> getOrders() throws ResourceException {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> resources = new ArrayList<>();
        for (Order order : orders){
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setDate(order.getDate());
            orderDto.setDetails(order.getDetails()
                    .stream().map(this::convertToResource2).collect(Collectors.toList()));
            resources.add(orderDto);

        }
        return resources;
    }

    @Override
    public OrderDto getOrderById(Long id) throws ResourceException {
        return convertToResource(orderRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) throws ResourceException, ParseException {
        Order order = new Order();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(formatter.format(new Date()));
        order.setDate(date);
        order.setTotalPrice(0d);
        order = orderRepository.save(order);
        Double total = 0d;
        List<CreateOrderDetailDto> orderDetails = createOrderDto.getDetails();
        for (CreateOrderDetailDto orderDetail : orderDetails) {
            OrderDetail orderDetailEntity = new OrderDetail();
            Product product = productRepository.findById(orderDetail.getProductId())
                    .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND"));
            orderDetailEntity.setOrder(order);
            //orderDetailEntity.setCreatedAt(date);
            orderDetailEntity.setProduct(product);
            orderDetailEntity.setUnitName(product.getName());
            orderDetailEntity.setUnitPrice(product.getProductPrice());
            orderDetailEntity.setQuantity(orderDetail.getQuantity());
            orderDetailEntity.setTotalPrice(product.getProductPrice() * orderDetail.getQuantity());
            total+= orderDetailEntity.getTotalPrice();
            orderDetailRepository.save(orderDetailEntity);
        }
        order.setTotalPrice(total);
        return convertToResource(orderRepository.save(order));
    }

    private Order convertToEntity(CreateOrderDto resource){return  modelMapper.map(resource, Order.class);}

    private OrderDto convertToResource(Order entity){return  modelMapper.map(entity,OrderDto.class);}

    private OrderDetailDto convertToResource2(OrderDetail entity){return  modelMapper.map(entity,OrderDetailDto.class);}

}
