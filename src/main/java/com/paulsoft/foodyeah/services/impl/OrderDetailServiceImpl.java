package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.OrderDetailDto.CreateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.UpdateOrderDetailDto;
import com.paulsoft.foodyeah.entities.Order;
import com.paulsoft.foodyeah.entities.OrderDetail;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.OrderDetailRepository;
import com.paulsoft.foodyeah.services.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public OrderDetailDto getOrderDetailById(Long id) throws ResourceException {

        return convertToResource(orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public List<OrderDetailDto> getOrderDetails() throws ResourceException {
        return orderDetailRepository.findAll()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDetailDto createOrderDetailDto(CreateOrderDetailDto createOrderDetailDto) throws ResourceException, ParseException {
        OrderDetail orderDetail = convertToEntity(createOrderDetailDto);
        if(orderDetailRepository.findById(orderDetail.getId()).isPresent()){
            throw  new NotFoundException("ORDER_DETAIL_ALREADY_EXISTS","ORDER_DETAIL_ALREADY_EXISTS");
        }
        orderDetail.setState(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(formatter.format(new Date()));
        orderDetail.setCreatedAt(date);
        return convertToResource(orderDetailRepository.save(orderDetail));
    }

    @Override
    @Transactional
    public OrderDetailDto updateOrderDetailDto(UpdateOrderDetailDto updateOrderDetailDto, Long id) throws ResourceException {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("NOT_FOUND","NOT_FOUND"));
        orderDetail.setProduct(updateOrderDetailDto.getProduct());
        orderDetail.setQuantity(updateOrderDetailDto.getQuantity());
        orderDetail.setOrder(updateOrderDetailDto.getOrder());
        return convertToResource(orderDetailRepository.save(orderDetail));
    }

    @Override
    public String deleteOrderDetailDto(Long id) throws ResourceException {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("NOT_FOUND","NOT_FOUND"));
        orderDetail.setState(false);
        orderDetailRepository.save(orderDetail);
        return orderDetail.getState().toString();
    }
    private OrderDetail convertToEntity(CreateOrderDetailDto resource){
        return modelMapper.map(resource,OrderDetail.class);
    }

    private OrderDetailDto convertToResource(OrderDetail entity){
        return modelMapper.map(entity,OrderDetailDto.class);
    }
}
