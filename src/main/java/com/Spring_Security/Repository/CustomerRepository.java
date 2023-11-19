package com.Spring_Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring_Security.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findByName(String name);
}
