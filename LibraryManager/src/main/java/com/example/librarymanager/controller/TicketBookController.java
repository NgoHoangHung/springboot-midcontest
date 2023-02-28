package com.example.librarymanager.controller;

import com.example.librarymanager.model.dto.TicketBookDTO;
import com.example.librarymanager.model.entity.TicketBook;
import com.example.librarymanager.service.TicketBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketbook")
public class TicketBookController {
    @Autowired
    private TicketBookService ticketBookService;

    @GetMapping("")
    public ResponseEntity<List<TicketBook>> getAll() {
        return ResponseEntity.ok(ticketBookService.getAll1());
    }

    @PostMapping("/rent")
    public ResponseEntity<String> rentBorrowTicket(@RequestBody TicketBookDTO dto) {
        return ResponseEntity.ok(ticketBookService.rentBook(dto));
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyBook(@RequestBody TicketBookDTO dto) {
        return ResponseEntity.ok(ticketBookService.buyBook(dto));
    }

    @PutMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody TicketBookDTO dto) {
        return ResponseEntity.ok(ticketBookService.returnBook(dto));
    }
}
