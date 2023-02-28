package com.example.librarymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private double price;
    private Integer quantity;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @JsonBackReference
    @OneToMany(mappedBy = "book")
    private List<BookManager> bookManagers;


}
