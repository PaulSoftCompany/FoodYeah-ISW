package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.UpdateCustomerDto;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.dtos.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto;
import com.paulsoft.foodyeah.entities.Customer;
import com.paulsoft.foodyeah.repositories.CustomerRepository;
import com.paulsoft.foodyeah.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public CustomerDto getCustomerByCode(String code) throws ResourceException {
        return convertToResource(customerRepository.findByCode(code)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public CustomerDto getCustomerById(Long id) throws ResourceException {
        return convertToResource(customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public List<CustomerDto> getCustomersByState(Boolean state) throws ResourceException {
        return customerRepository.findAllByState(state)
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getCustomers() throws ResourceException {
        return customerRepository.findAll()
                .stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) throws ResourceException, ParseException {
        Customer customer = convertToEntity(createCustomerDto);
        if(customerRepository.findByCode(createCustomerDto.getCode()).isPresent()){
            throw new NotFoundException("CUSTOMER_EXISTS","CUSTOMER_EXISTS");
        }
        customer.setState(true);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(formatter.format(new Date()));
        customer.setCreatedAt(date);
        return convertToResource(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(UpdateCustomerDto updateCustomerDto, Long id) throws ResourceException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND"));
        customer.setPassword(updateCustomerDto.getPassword());
        return convertToResource(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public String deleteCustomer(Long id) throws ResourceException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND"));
        customer.setState(false);
        customerRepository.save(customer);
        return customer.getCode();
    }

    private Customer convertToEntity(CreateCustomerDto resource){return  modelMapper.map(resource, Customer.class);}

    private CustomerDto convertToResource(Customer entity){return  modelMapper.map(entity,CustomerDto.class);}
}