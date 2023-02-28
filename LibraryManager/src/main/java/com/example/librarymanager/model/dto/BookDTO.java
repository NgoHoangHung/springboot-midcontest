package com.example.librarymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Data
public class BookDTO {
    private int id;
    private String author;
    private String name;
    private double price;
    private TypeDTO type;
    private int quantityTransactions;
}
