package com.groupeisi.com.service;

import com.groupeisi.com.dto.Order;
import com.groupeisi.com.mapper.OrderMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private Logger logger = LogManager.getLogger(OrderService.class);
    private List<Order> orderList = new ArrayList<>();

    public Order addOrder(Order order) {
        logger.info("OrderService:addOrder execution started...");
        logger.info("OrderService:addOrder request payload {}", OrderMapper.mapToJsonString(order));
        order.setOrderPlacedDate(new Date());
        order.setTransactionId(UUID.randomUUID().toString());
        orderList.add(order);
        logger.info("OrderService:addOrder response {}", OrderMapper.mapToJsonString(order));
        logger.info("OrderService:addOrder execution ended...");
        return order;
    }

    public List<Order> getOrders() {
        logger.info("OrderService:getOrders execution started...");
        logger.info("OrderService:getOrders response {}", OrderMapper.mapToJsonString(orderList));
        logger.info("OrderService:getOrders execution ended...");
        return orderList;
    }

    public Order getOrder(int id) {
        logger.info("OrderService:getOrder execution started...");
        Order order =  orderList.stream()
                .filter(o -> o.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Not found Order with id " + id));

        logger.info("OrderService:getOrder execution ended...");
        return order;
    }
}
