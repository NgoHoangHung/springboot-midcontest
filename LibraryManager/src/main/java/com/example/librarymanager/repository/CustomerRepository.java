package com.example.librarymanager.repository;

import com.example.librarymanager.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByPhone(String phone);

    Customer findByPhone(String phone);
}
