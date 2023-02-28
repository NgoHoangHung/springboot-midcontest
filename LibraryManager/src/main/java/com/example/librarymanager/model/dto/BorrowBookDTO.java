package com.example.librarymanager.model.dto;

import com.example.librarymanager.model.entity.BookManager;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BorrowBookDTO {
    private String customerName;
    private String customerPhone;
    private String customerCCCD;
    private List<BookManagerDTO> bookManagerDTOS;
    private Double deposit;
}
