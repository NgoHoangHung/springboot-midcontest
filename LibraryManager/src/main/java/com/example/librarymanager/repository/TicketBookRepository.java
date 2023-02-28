package com.example.librarymanager.repository;

import com.example.librarymanager.model.entity.TicketBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketBookRepository extends JpaRepository<TicketBook, Integer> {
    TicketBook findById(int id);
}
