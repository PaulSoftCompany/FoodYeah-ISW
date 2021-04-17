package com.example.service.impl;

import com.example.entity.Card;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.repository.CardRepository;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sound.sampled.BooleanControl;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CardRepository cardRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
            ProductRepository productRepository, CardRepository cardRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Order> findOrderAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        PrepareDetail(order);
        PrepareHeader(order);
        return orderRepository.save(order);
    }

    private void PrepareHeader(Order order) {
        order.setState("CREATED");
        order.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        order.setTotalPrice(OrderTotalPrice(order));
        order.setInittime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        order.setEndtime("00:00:00");
    }

    private void PrepareDetail(Order order) {
        List<OrderDetail> _orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : _orderDetails) {
            Product product = productRepository.getOne(orderDetail.getProduct().getId());
            orderDetail.setUnitPrice(product.getProductPrice());
            orderDetail.setTotalPrice(orderDetail.getUnitPrice() * orderDetail.getQuantity());
            orderDetail.setState("CREATED");
            orderDetailRepository.save(orderDetail);
        }
    }

    public void SetEndTime(Order order) {
        order.setEndtime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        order.setState("DELIVERED");
        orderRepository.save(order);
    }

    public void DecreaseStock(Order order) {
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            Product product = orderDetail.getProduct();
            product.setStock((byte) (orderDetail.getProduct().getStock() - orderDetail.getQuantity()));
            productRepository.save(product);
        }
    }

    public String GetAverageTime() {
        String s = "00:00:00";
        Long averageTime = 0L;
        int quant = 0;
        for (Order order : orderRepository.findAll()) {
            if (order.getEndtime() == "00:00:00")
                continue;
            quant++;

            LocalDateTime _inittime = LocalDateTime.parse("2018-11-03T" + order.getInittime());
            LocalDateTime _endtime = LocalDateTime.parse("2018-11-03T" + order.getEndtime());

            averageTime += _inittime.until(_endtime, ChronoUnit.MINUTES);
        }

        if (quant == 0)
            return "5";
            
        averageTime /= quant;

        if (averageTime == 0)
            return "1";

        return averageTime.toString();
    }

    private float OrderTotalPrice(Order order) {
        float TotalPrice = 0;
        List<OrderDetail> _orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : _orderDetails) {
            TotalPrice += orderDetail.getTotalPrice();
        }
        return TotalPrice;
    }

    public boolean DecreaseCostumerMoney(long cardId, long orderId)
        {
            Order order = getOrder(orderId);
            Card card = cardRepository.getOne(cardId);

            if ((card.getCardMoney() - order.getTotalPrice()) >= 0)
            {
                card.setCardMoney(card.getCardMoney() - order.getTotalPrice());
                cardRepository.save(card);
                return true;
            }
            return false;
        }
}
