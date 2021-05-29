package com.paulsoft.foodyeah.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulsoft.foodyeah.entities.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String code;
    private Boolean state;
    //fijarnos lo del password
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdAt;

    List<Card> registeredCards;
}
