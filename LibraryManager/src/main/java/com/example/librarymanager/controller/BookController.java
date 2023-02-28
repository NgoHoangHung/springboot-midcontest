package com.example.librarymanager.controller;

import com.example.librarymanager.model.entity.Book;
import com.example.librarymanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Book>> getByTicketId(@PathVariable int id) {
        return ResponseEntity.ok(bookService.getByTicketId(id));
    }
}
