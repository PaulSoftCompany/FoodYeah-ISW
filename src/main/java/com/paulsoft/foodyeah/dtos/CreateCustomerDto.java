package com.paulsoft.foodyeah.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerDto {
    private String firstName;
    private String lastName;
    private String code;
    private String password;
}
