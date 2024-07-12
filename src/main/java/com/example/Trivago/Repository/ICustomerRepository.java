package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUserName(String userName);
}
