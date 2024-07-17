package com.example.Trivago.Repository;

import com.example.Trivago.Entity.Customer;
import com.example.Trivago.Entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPeopleRepository extends JpaRepository<People,Long> {
    @Query("SELECT p FROM People p WHERE p.email = :userName")
    Optional<People>FindByUserName(String userName);

    @Query("SELECT p FROM People p")
    List<People> findAllPeopleBooking();
}
