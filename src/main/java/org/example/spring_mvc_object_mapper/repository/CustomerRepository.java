package org.example.spring_mvc_object_mapper.repository;

import org.example.spring_mvc_object_mapper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
