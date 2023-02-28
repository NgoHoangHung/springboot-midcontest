package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.ChargeMoney;
import com.example.librarymanager.model.dto.WalletDTO;
import com.example.librarymanager.model.entity.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {
    String generateID();
    String insertWallet(WalletDTO dto);

    public Wallet getById(int id);

    public List<Wallet> getAll();

    public void createWallet(WalletDTO dto);

    public String chargeWallet(ChargeMoney chargeMoney);

    public String deleteWallet(int id);
}
