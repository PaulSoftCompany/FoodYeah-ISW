package com.paulsoft.foodyeah.dtos.CardDto;

import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateCardDto {
    private Boolean type;
    private String owner;
    private Long cardNumber;
    private Date expireDate;
    private Long customerId;
    private Double cardMoney;
}
