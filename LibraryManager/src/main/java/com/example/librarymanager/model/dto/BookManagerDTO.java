package com.example.librarymanager.model.dto;

import com.example.librarymanager.model.entity.Book;
import com.example.librarymanager.model.entity.TicketBook;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class BookManagerDTO {
    private int id;

    private BookDTO bookDTO;

    private int quantity;

}
