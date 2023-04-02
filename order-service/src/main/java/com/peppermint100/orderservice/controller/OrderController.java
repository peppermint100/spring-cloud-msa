package com.peppermint100.orderservice.controller;

import com.peppermint100.orderservice.dto.OrderDto;
import com.peppermint100.orderservice.jpa.OrderEntity;
import com.peppermint100.orderservice.service.OrderService;
import com.peppermint100.orderservice.vo.RequestOrder;
import com.peppermint100.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {
    private final Environment env;
    private final OrderService service;

    public OrderController(Environment env, OrderService service) {
        this.env = env;
        this.service = service;
    }
    @GetMapping("/health-check")
    public String status() {
        return String.format("Order Service is Working on PORT : %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createUser(
            @PathVariable("userId") String userId,
            @RequestBody RequestOrder order) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderDto orderDto = mapper.map(order, OrderDto.class);
        orderDto.setUserId(userId);
        service.createOrder(orderDto);
        ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(
            @PathVariable("userId") String userId
    ) {
        Iterable<OrderEntity> orders = service.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();

        orders.forEach(o -> {
            result.add(new ModelMapper().map(o, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
