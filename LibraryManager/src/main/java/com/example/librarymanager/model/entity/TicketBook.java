package com.example.librarymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table
@Getter
@Setter
public class TicketBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Servicez servicez;
    @JsonBackReference
    @OneToMany(mappedBy = "ticketBook",cascade = CascadeType.ALL,fetch =  FetchType.EAGER)
    private List<BookManager> bookManagers;

    private LocalDate creatAt;
    private LocalDate returnDay;
    private String note;
    private double totalPrice;


}
