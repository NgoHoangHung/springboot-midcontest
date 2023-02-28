package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.CustomerDTO;
import com.example.librarymanager.model.entity.Customer;
import com.example.librarymanager.model.entity.Wallet;
import com.example.librarymanager.repository.CustomerRepository;
import com.example.librarymanager.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletServiceImpl walletService;

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Transactional
    @Override
    public Customer creatCustomer(CustomerDTO dto) {
        if (!customerRepository.existsByPhone(dto.getPhone())) {
            ModelMapper mapper = new ModelMapper();
            Customer newcustomer = new Customer();
            newcustomer.setName(dto.getName());
            newcustomer.setPhone(dto.getPhone());
            newcustomer.setCccd(dto.getCccd());
            if (dto.getWallet() == null ||
                    !walletRepository.existsByAccountNum(dto.getWallet().getAccountnum())) {
                Wallet input = new Wallet();
                input.setAccountNum(walletService.generateID());
                input.setBalance(0);
                walletRepository.save(input);
                newcustomer.setWallet(input);
            } else {
                newcustomer.setWallet(mapper.map(dto.getWallet(), Wallet.class));
            }
            customerRepository.save(newcustomer);
            return newcustomer;
        }
        return null;
    }

    @Override
    public String updateCustomer(CustomerDTO dto) {
        return null;
    }

    @Override
    public String deleteCustomer(int id) {
        return null;
    }
}
