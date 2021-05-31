package com.paulsoft.foodyeah.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="customers")
public class Customer{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",unique=true,nullable=false)
    private Long id;

    @Column(name="first_name", length=40)
    private String firstName;

    @Column(name ="last_name",length = 40)
    private String lastName;

    private String code;

    private Boolean state;

    private String password;

    @Column(name ="created_at")
    private Date createdAt;

    @ManyToOne
    List<Card> registeredCards;

}