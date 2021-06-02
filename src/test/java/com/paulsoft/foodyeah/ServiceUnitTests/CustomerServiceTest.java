package com.paulsoft.foodyeah.ServiceUnitTests;

import com.paulsoft.foodyeah.dtos.CustomerDto.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.UpdateCustomerDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.entities.Customer;
import com.paulsoft.foodyeah.entities.ProductCategory;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CustomerRepository;
import com.paulsoft.foodyeah.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CustomerServiceTest {
    @InjectMocks
    CustomerServiceImpl service;
    @Mock
    CustomerRepository repository;
    public static final ModelMapper modelMapper = new ModelMapper();

    private static final Customer CUSTOMER = new Customer();
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRSTNAME = "NombreDelCliente";
    private static final String CUSTOMER_LASTNAME = "ApellidoDelCliente";
    private static final String CUSTOMER_CODE = "U20181ALGO";
    private static final String CUSTOMER_PASSWORD = "asd123ASD#";
    private static final Boolean STATE_CREATED = true;
    private static final Boolean STATE_DELETED = false;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        CUSTOMER.setId(CUSTOMER_ID);
        CUSTOMER.setFirstName(CUSTOMER_FIRSTNAME);
        CUSTOMER.setLastName(CUSTOMER_LASTNAME);
        CUSTOMER.setCode(CUSTOMER_CODE);
        CUSTOMER.setPassword(CUSTOMER_PASSWORD);
    }

    @Test
    public void getCustomerByCode() throws Exception{
        String methodName = "GET CUSTOMER BY CODE";
        //Mock
        Mockito.when(repository.findByCode(CUSTOMER_CODE)).thenReturn(Optional.empty());
        try{
            service.getCustomerByCode(CUSTOMER_CODE);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findByCode(CUSTOMER_CODE)).thenReturn(Optional.of(CUSTOMER));
        CustomerDto response;
        //Get response
        try {
            response = service.getCustomerByCode(CUSTOMER_CODE);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map Customer to CustomerDto:
        CustomerDto mapped =  convertToResource(CUSTOMER);
        //Assertions:
        try {
            validateCustomer(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    public void getCustomerById() throws Exception{
        String methodName = "GET CUSTOMER BY ID";
        //Mock
        Mockito.when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        try{
            service.getCustomerById(CUSTOMER_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
        CustomerDto response;
        //Get response
        try {
            response = service.getCustomerById(CUSTOMER_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map Customer to CustomerDto:
        CustomerDto mapped =  convertToResource(CUSTOMER);
        //Assertions:
        try {
            validateCustomer(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    public void getCustomersByState() throws Exception{
        String methodName = "GET CUSTOMER BY STATE";
        //Mock
        Mockito.when(repository.findAllByState(STATE_CREATED)).thenReturn(Arrays.asList(CUSTOMER));
        List<CustomerDto> response;
        //Get response
        try {
            response = service.getCustomersByState(STATE_CREATED);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map Customer to CustomerDto:
        CustomerDto mapped =  convertToResource(CUSTOMER);
        //Assertions:
        try {
            validateCustomer(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    public void getCustomers() throws Exception{
        String methodName = "GET CUSTOMER BY STATE";
        //Mock
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(CUSTOMER));
        List<CustomerDto> response;
        //Get response
        try {
            response = service.getCustomers();
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map Customer to CustomerDto:
        CustomerDto mapped =  convertToResource(CUSTOMER);
        //Assertions:
        try {
            validateCustomer(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    public void createCustomer() throws Exception{
        String methodName = "CREATE CUSTOMER";
        //Mock
        Mockito.when(repository.findByCode(CUSTOMER_CODE)).thenReturn(Optional.of(CUSTOMER));
        //Map entities:
        CreateCustomerDto source = convertToCreateResource(CUSTOMER);
        //Primero validamos el error de customer con codigo ya creada:
        try{
            service.createCustomer(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findByCode(CUSTOMER_CODE)).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(Customer.class))).thenReturn(CUSTOMER);
        CustomerDto response;
        //Get response
        try {
            response = service.createCustomer(source);
        }catch(ResourceException|ParseException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        CustomerDto mapped = convertToResource(CUSTOMER);
        //Assertions:
        try {
            validateCustomer(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    public void updateCustomer() throws Exception{
        String methodName = "UPDATE PRODUCT CATEGORY";
        Mockito.when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        //Map entities:
        UpdateCustomerDto toupdate =  convertToUpdateResource(CUSTOMER);
        //Primero validamos el error de customer con codigo ya creada:
        try{
            service.updateCustomer(toupdate,CUSTOMER_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        //Mock
        CustomerDto response;
        Mockito.when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
        Mockito.when(repository.save(Mockito.any(Customer.class))).thenReturn(CUSTOMER);
        //Get response
        try {
            response = service.updateCustomer(toupdate,CUSTOMER_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        CustomerDto mapped = convertToResource(CUSTOMER);
        //Assertions:
        try {
            validateCustomer(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    public void deleteCustomer() throws Exception{
        String methodName = "DELETE CUSTOMER";
        //Mock
        Mockito.when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(Customer.class))).thenReturn(CUSTOMER);
        String response;
        try{
            service.deleteCustomer(CUSTOMER_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
        try {
            response = service.deleteCustomer(CUSTOMER_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            Util.assertEquals("GET CODE",response, CUSTOMER_CODE);
            Util.assertEquals("GET STATE",CUSTOMER.getState(), STATE_DELETED);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    private void validateCustomer(CustomerDto entity, CustomerDto mapped) throws AssertionError{
        Util.assertNotNull("CUSTOMER NOT NULL",entity);
        Util.assertEquals("CUSTOMER ID",entity.getId(),mapped.getId());
        Util.assertEquals("CUSTOMER FIRSTNAME",entity.getFirstName(),mapped.getFirstName());
        Util.assertEquals("CUSTOMER LASTNAME",entity.getLastName(),mapped.getLastName());
        Util.assertEquals("CUSTOMER CODE",entity.getCode(),mapped.getCode());
        Util.assertEquals("CUSTOMER STATE",entity.getState(),mapped.getState());
        Util.assertEquals("CUSTOMER DATE",entity.getCreatedAt(),mapped.getCreatedAt());
    }
    private CreateCustomerDto convertToCreateResource(Customer entity){
        return modelMapper.map(entity,CreateCustomerDto.class);
    }
    private UpdateCustomerDto convertToUpdateResource(Customer entity){
        return modelMapper.map(entity,UpdateCustomerDto.class);
    }
    private CustomerDto convertToResource(Customer entity){
        return modelMapper.map(entity,CustomerDto.class);
    }
}
