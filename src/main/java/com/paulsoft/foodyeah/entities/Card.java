package com.paulsoft.foodyeah.entities;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="cards")
public class Card{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",unique=true,nullable=false)
    private Long id;

    private Boolean type;

    @Column(name="owner",length=40)
    private String owner;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "expire_date")
    private Date expireDate;

    @ManyToMany(mappedBy = "registeredCards")
    List<Customer> customers;
}