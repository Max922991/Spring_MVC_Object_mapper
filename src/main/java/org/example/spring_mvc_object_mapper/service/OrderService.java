package org.example.spring_mvc_object_mapper.service;

import org.example.spring_mvc_object_mapper.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
}