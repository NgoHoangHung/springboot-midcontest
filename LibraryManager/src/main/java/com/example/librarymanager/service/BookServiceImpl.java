package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.BookDTO;
import com.example.librarymanager.model.entity.Book;
import com.example.librarymanager.repository.BookRepository;
import com.example.librarymanager.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TypeRepository typeRepository;


    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getByTicketId(int id) {
        return bookRepository.findBooksByTicketBookId(id);
    }

    @Override
    public String insertBook(BookDTO dto) {
        return "đã thêm thành công";
    }

    @Override
    public void createBook(BookDTO dto) {
        if (bookRepository.existsById(dto.getId())) {
            Book book = new Book();
            book.setName(dto.getName());
            book.setAuthor(dto.getAuthor());
            book.setType(typeRepository.findById(dto.getType().getId()));
            book.setQuantity(dto.getQuantityTransactions());
            book.setPrice(dto.getPrice());
        }
    }

    @Override
    public Book updateBook(BookDTO dto) {

        return null;
    }

    @Override
    public String deleteBook(int id) {
        return null;
    }


}
