package com.example.librarymanager.repository;

import com.example.librarymanager.model.entity.BookManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookManagerRepository extends JpaRepository<BookManager, Integer> {
    @Query("SELECT bm.book FROM BookManager bm JOIN bm.book JOIN bm.ticketBook t WHERE t.id = :id")
    List<BookManager> findBookManagersByTicketBookId(int id);

    BookManager findById(int id);
}
