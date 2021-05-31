package com.paulsoft.foodyeah.services;

import com.paulsoft.foodyeah.dtos.CustomerDto.UpdateCustomerDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.dtos.CustomerDto.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    CustomerDto getCustomerByCode(String code) throws ResourceException;

    CustomerDto getCustomerById(Long id) throws ResourceException;

    List<CustomerDto> getCustomersByState(Boolean state) throws ResourceException;

    List<CustomerDto> getCustomers() throws ResourceException;

    CustomerDto createCustomer(CreateCustomerDto createCustomerDto) throws ResourceException, ParseException;

    CustomerDto updateCustomer(UpdateCustomerDto updateCustomerDto, Long id) throws ResourceException;

    String deleteCustomer(Long id) throws ResourceException;


}