package com.example.librarymanager.repository;

import com.example.librarymanager.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    boolean existsByAccountNum(String account);

    Wallet findByAccountNum(String account);

    @Query("SELECT w FROM Wallet w JOIN Customer c WHERE c.phone = :phone")
    Wallet findByCustomerPhone(String phone);


//    boolean existsByBorrower_Phone(String phone);
}
