package com.portfolio.pedidos.controller;

import com.portfolio.pedidos.dto.CreateOrderRequest;
import com.portfolio.pedidos.dto.OrderResponse;
import com.portfolio.pedidos.dto.ShippingOptionResponse;
import com.portfolio.pedidos.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return OrderResponse.from(orderService.createOrder(request));
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable String id) {
        return OrderResponse.from(orderService.findById(id));
    }

    @GetMapping("/shipping/options")
    public List<ShippingOptionResponse> shippingOptions() {
        return orderService.listShippingOptions();
    }

    @GetMapping("/settings")
    public Map<String, String> settings() {
        return orderService.appSettings();
    }
}
