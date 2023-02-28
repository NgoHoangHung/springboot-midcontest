package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.TicketBookDTO;
import com.example.librarymanager.model.entity.Book;
import com.example.librarymanager.model.entity.TicketBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TicketBookService {
    public TicketBook getById(int id);
    public List<TicketBook> getAll();
    public List<TicketBook> getAll1();
    public String rentBook(TicketBookDTO dto);
    public String buyBook(TicketBookDTO dto);
    public String returnBook(TicketBookDTO dto);
    public String lostBookTicket(TicketBookDTO dto);

    public String updateTicket(TicketBookDTO dto);
    public String deleteTicket(int id);

}
