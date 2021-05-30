package com.paulsoft.foodyeah.controllers;

import com.paulsoft.foodyeah.dtos.CustomerDto.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.UpdateCustomerDto;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.exceptions.responses.ResourceResponse;
import com.paulsoft.foodyeah.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping("customers?state={state}")
    public ResourceResponse<List<CustomerDto>> getCustomersByState(@PathVariable("state") Boolean state) throws ResourceException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.getCustomersByState(state));
    }
    //TODO: Corregir el de state (no sale correcto)

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("customers")
    public ResourceResponse<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto ) throws ResourceException, ParseException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.createCustomer(createCustomerDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("customers/{id}")
    public ResourceResponse<CustomerDto> getCustomerById(@PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.getCustomerById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("customers?code={code}")
    public ResourceResponse<CustomerDto> getCustomerByCode(@PathVariable("code") String code) throws ResourceException{
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.getCustomerByCode(code));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("customers/{id}")
    public ResourceResponse<String> deleteCustomer(@PathVariable("id") Long id)throws ResourceException {
        return new ResourceResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                customerService.deleteCustomer(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("customers/{id}")
    public ResourceResponse<CustomerDto> updateCustomer(@RequestBody @Valid UpdateCustomerDto updateCustomerDto, @PathVariable("id") Long id) throws ResourceException{
        return new ResourceResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                customerService.updateCustomer(updateCustomerDto,id));
    }

}
