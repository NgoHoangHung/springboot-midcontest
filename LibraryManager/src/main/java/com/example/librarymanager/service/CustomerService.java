package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.CustomerDTO;
import com.example.librarymanager.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public Customer getById(int id);
    public List<Customer> getAll();
    public Customer creatCustomer(CustomerDTO dto);
    public String updateCustomer(CustomerDTO dto);
    public String deleteCustomer(int id);
}
