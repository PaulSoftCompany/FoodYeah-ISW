package com.paulsoft.foodyeah.controllers;

import com.paulsoft.foodyeah.dtos.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.exceptions.responses.ResourceResponse;
import com.paulsoft.foodyeah.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("customers")
    public ResourceResponse<List<CustomerDto>> getCustomers() throws ResourceException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.getCustomers());
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("customers")
    public ResourceResponse<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto ) throws ResourceException, ParseException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.createCustomer(createCustomerDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("customers/{id}")
    public ResourceResponse<String> deleteCustomer(@PathVariable("id") Long id)throws ResourceException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.deleteCustomer(id));
    }

    //TODO: acabar los demas endpoints
}
