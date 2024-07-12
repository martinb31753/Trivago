package com.example.Trivago.Repository;

import com.example.Trivago.Entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPeopleRepository extends JpaRepository<People , Long> {

    Optional<People>findByName (String name);

    People save(People person);
}
