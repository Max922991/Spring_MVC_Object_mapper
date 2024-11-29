package org.example.spring_mvc_object_mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import org.example.spring_mvc_object_mapper.model.Customer;
import org.example.spring_mvc_object_mapper.model.Order;
import org.example.spring_mvc_object_mapper.model.Product;
import org.example.spring_mvc_object_mapper.repository.OrderRepository;
import org.example.spring_mvc_object_mapper.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    @BeforeEach
    public void setUp() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setLastName("Ivan Ivanov");

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Product 1");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Product 2");
        products.add(product2);

        order = new Order();
        order.setOrderId(1L);
        order.setCustomer(customer);
        order.setProducts(products);
        order.setOrderDate("2024-11-29");
        order.setShippingAddress("Moscow");
        order.setTotalPrice(99.99);
        order.setOrderStatus("Pending");
    }

    @Test
    public void testCreateOrder() {
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertEquals(order.getOrderId(), createdOrder.getOrderId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetOrderById_Success() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(1L);

        assertEquals(order.getOrderId(), foundOrder.getOrderId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetOrderById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrderById(1L);
        });

        assertEquals("Order not found", exception.getMessage());
        verify(orderRepository, times(1)).findById(1L);
    }
}
