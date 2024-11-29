package org.example.spring_mvc_object_mapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.spring_mvc_object_mapper.controller.OrderController;
import org.example.spring_mvc_object_mapper.model.Customer;
import org.example.spring_mvc_object_mapper.model.Order;
import org.example.spring_mvc_object_mapper.model.Product;
import org.example.spring_mvc_object_mapper.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setOrderId(1L);
        order.setCustomer(new Customer());
        order.setProducts(Arrays.asList(new Product(), new Product()));
        order.setOrderDate("2023-10-01");
        order.setShippingAddress("Moscow");
        order.setTotalPrice(100.0);
        order.setOrderStatus("PENDING");

        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customer\":{},\"products\":[{},{}],\"orderDate\":\"2023-10-01\",\"shippingAddress\":\"Moscow\",\"totalPrice\":100.0,\"orderStatus\":\"PENDING\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.orderStatus").value("PENDING"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setOrderId(1L);
        order.setCustomer(new Customer());
        order.setProducts(Arrays.asList(new Product(), new Product()));
        order.setOrderDate("2023-10-01");
        order.setShippingAddress("Moscow");
        order.setTotalPrice(100.0);
        order.setOrderStatus("PENDING");

        when(orderService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(1L))
                .andExpect(jsonPath("$.orderStatus").value("PENDING"));
    }
}
