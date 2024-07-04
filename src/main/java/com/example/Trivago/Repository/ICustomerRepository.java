package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
