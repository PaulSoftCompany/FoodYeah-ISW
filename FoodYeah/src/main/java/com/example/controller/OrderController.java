package com.example.controller;

import com.example.entity.Card;
import com.example.entity.Order;
import com.example.service.CardService;
import com.example.service.OrderService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listAllOrders(@RequestParam(name = "orderId", required = false) Long orderId) {
        List<Order> orders = new ArrayList<>();
        if (null == orderId) {
            orders = orderService.findOrderAll();
            if (orders.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") long id) {
        log.info("Fetching Orders with Id {}", id);
        Order order = orderService.getOrder(id);
        if (null == order) {
            log.error("Order with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order, BindingResult result) {
        log.info("Creating Order : {}", order);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Order orderDB = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDB);
    }

    @GetMapping("/getAverageTime")
    public Map<String, String> getAverageTime() {
        HashMap<String, String> ResponseJSON = new HashMap<>();
        ResponseJSON.put("AverageTime", orderService.GetAverageTime().toString() + " minutes.");
        return ResponseJSON;
    }

    @PutMapping("/{orderId}/card={cardId}")
    public ResponseEntity<Order> orderDelivered(@PathVariable("cardId") long cardId,
            @PathVariable("orderId") long orderId) {
        log.info("Fetching Orders with Id {}", orderId);
        Order order = orderService.getOrder(orderId);
        String orderState = order.getState();

        if (!orderState.equals("CREATED")) {
            log.error("La orden a pagar ya se ha entregado o ha sido eliminado", orderId);
            return ResponseEntity.noContent().build();
        }

        if (orderService.DecreaseCostumerMoney(cardId, orderId)) {
            orderService.SetEndTime(order);
            orderService.DecreaseStock(order);

            if (null == order) {
                log.error("Order with id {} not found.", orderId);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(order);
        } else {
            log.error("No hay suficiente dinero en la tarjeta para pagar la orden", orderId);
            return ResponseEntity.noContent().build();
        }
    }
}
