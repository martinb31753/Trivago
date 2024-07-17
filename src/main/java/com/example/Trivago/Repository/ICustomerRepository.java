package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Customer;
import com.example.Trivago.Entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c WHERE c.userName = :userName")
    Optional<Customer> FindByUserName(String userName);
}
