package org.example.spring_mvc_object_mapper.repository;

import org.example.spring_mvc_object_mapper.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}