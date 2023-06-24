package com.groupeisi.com.controller;

import com.groupeisi.com.dto.Order;
import com.groupeisi.com.mapper.OrderMapper;
import com.groupeisi.com.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    Logger logger = LogManager.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public Order placeOrder(@RequestBody Order order){
        logger.info("OrderController:placeOrder persist order request {}", OrderMapper.mapToJsonString(order));
        Order orderAdded = orderService.addOrder(order);
        logger.info("OrderController:placeOrder response from service {}", OrderMapper.mapToJsonString(orderAdded));
        return orderAdded;
    }

    @GetMapping
    public List<Order> getOrders(){
        List<Order> orders = orderService.getOrders();
        logger.info("OrderController:placeOrder response from service {}", OrderMapper.mapToJsonString(orders));
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id){
        logger.info("OrderController:placeOrder fetch order by id={}", id);
        Order order = orderService.getOrder(id);
        logger.info("OrderController:placeOrder fetch order response {}", OrderMapper.mapToJsonString(order));
        return order;
    }
}
