package com.example.Trivago.Repository;

import com.example.Trivago.Entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<People,Long> {
}
