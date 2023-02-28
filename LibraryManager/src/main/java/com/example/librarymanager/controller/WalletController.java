package com.example.librarymanager.controller;

import com.example.librarymanager.model.dto.ChargeMoney;
import com.example.librarymanager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PutMapping("")
    public ResponseEntity<String> chargeWallet(@RequestBody ChargeMoney chargeMoney) {
        return ResponseEntity.ok( walletService.chargeWallet(chargeMoney));
    }
}