package com.paulsoft.foodyeah.dtos.CardDto;

import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import com.paulsoft.foodyeah.entities.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardDto {
    private Long id;
    private Boolean type;
    private String owner;
    private Long cardNumber;
    private Date expireDate;
    private CustomerDto customer;
    private Double cardMoney;
}
