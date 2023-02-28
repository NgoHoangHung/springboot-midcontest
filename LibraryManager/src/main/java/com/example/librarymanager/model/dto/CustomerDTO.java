package com.example.librarymanager.model.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String phone;
    private String cccd;
    private WalletDTO wallet;
}
