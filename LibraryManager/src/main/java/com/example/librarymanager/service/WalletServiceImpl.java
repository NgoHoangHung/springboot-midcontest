package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.ChargeMoney;
import com.example.librarymanager.model.dto.WalletDTO;
import com.example.librarymanager.model.entity.Wallet;
import com.example.librarymanager.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public String insertWallet(WalletDTO dto) {
        Wallet newWallet = new Wallet();
        newWallet.setAccountNum(generateID());
        newWallet.setBalance(0);
        walletRepository.save(newWallet);
        return "thêm ví thành công";
    }

    public String generateID() {
        int accountID = (int) (Math.random() * (999999 - 100001 + 1) + (100001));
        while (walletRepository.existsByAccountNum("A" + accountID)) {
            accountID = (int) (Math.random() * (999999 - 100001 + 1) + (100001));
        }
        return "A" + accountID;

    }

    @Override
    public Wallet getById(int id) {
        return null;
    }

    @Override
    public List<Wallet> getAll() {
        return null;
    }

    @Override
    public void createWallet(WalletDTO dto) {

    }

    @Override
    public String chargeWallet(ChargeMoney chargeMoney) {

        Wallet wallet = walletRepository.findByAccountNum(chargeMoney.getWallet().getAccountNum());
        wallet.setBalance(wallet.getBalance() + chargeMoney.getDeposit());
        LocalDate date = LocalDate.now();
        walletRepository.save(wallet);
        return "nạp tiền thành công lúc " + date;
    }

    @Override
    public String deleteWallet(int id) {
        return null;
    }
}
