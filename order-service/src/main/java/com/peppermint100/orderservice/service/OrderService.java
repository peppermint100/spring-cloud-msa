package com.peppermint100.orderservice.service;

import com.peppermint100.orderservice.dto.OrderDto;
import com.peppermint100.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
