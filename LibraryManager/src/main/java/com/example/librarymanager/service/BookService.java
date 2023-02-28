package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.BookDTO;
import com.example.librarymanager.model.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public Book getById(int id);
    public List<Book> getAll();
    public List<Book> getByTicketId(int id);
    public String insertBook(BookDTO dto);
    public void createBook(BookDTO dto);
    public Book updateBook(BookDTO dto);
    public String deleteBook(int id);
}
