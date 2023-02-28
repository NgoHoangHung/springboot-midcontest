package com.example.librarymanager.model.dto;

import com.example.librarymanager.model.entity.BookManager;
import com.example.librarymanager.model.entity.Servicez;
import lombok.Data;

import java.util.List;


@Data
public class TicketBookDTO {
    private int id;
    private CustomerDTO customerDTO;
    private Servicez servicez;
    private List<BookManagerDTO> bookManagerDTOS;
    private int returnDay;

}
