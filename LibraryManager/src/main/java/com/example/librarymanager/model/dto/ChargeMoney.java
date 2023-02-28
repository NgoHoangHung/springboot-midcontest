package com.example.librarymanager.model.dto;

import com.example.librarymanager.model.entity.Wallet;
import lombok.Data;

@Data
public class ChargeMoney {
    private Wallet wallet;
    private Double deposit;
}
