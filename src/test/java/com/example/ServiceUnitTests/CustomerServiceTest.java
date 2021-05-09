package com.example.ServiceUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.example.entity.Customer;
import com.example.entity.CustomerCategory;
import com.example.entity.Role;
import com.example.repository.CustomerRepository;
import com.example.service.impl.CustomerServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerServiceTest {
    private static final Customer CUSTOMER = new Customer();
    private static final Long CUSTOMER_ID = 1L;
    private static final CustomerCategory CUSTOMER_CATEGORY = new CustomerCategory();
    private static final Long CUSTOMER_CATEGORY_ID = 1L;
    private static final String CUSTOMER_CATEGORY_NAME = "Alumno";
    private static final String CUSTOMER_CATEGORY_DESCRIPTION = "La categoria de alumno";
    private static final Role USER_ROLE = new Role();
    private static final Long USER_ROLE_ID = 1L;
    private static final String USER_ROLENAME = "Alumno";
    private static final List<Role> CUSTOMER_ROLES = Arrays.asList(USER_ROLE);
    private static final String CUSTOMER_NAME = "Alexis Enrique";
    private static final byte CUSTOMER_AGE = 21;
    private static final String CUSTOMER_USERNAME = "U20181A146";
    private static final String CUSTOMER_PASSWORD = "abcDEF#1234";

    private static final String STATE_CREATED = "CREATED";
    private static final String STATE_UPDATED = "UPDATED";
    private static final String STATE_DELETED = "DELETED";
    @InjectMocks
    CustomerServiceImpl customerServiceImpl;
    @Mock
    CustomerRepository customerRepository;
    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        USER_ROLE.setId(USER_ROLE_ID);
        USER_ROLE.setRoleName(USER_ROLENAME);

        CUSTOMER_CATEGORY.setId(CUSTOMER_CATEGORY_ID);
        CUSTOMER_CATEGORY.setCustomerCategoryName(CUSTOMER_CATEGORY_NAME);
        CUSTOMER_CATEGORY.setCustomerCategoryDescription(CUSTOMER_CATEGORY_DESCRIPTION);

        CUSTOMER.setId(CUSTOMER_ID);
        CUSTOMER.setCustomerCategory(CUSTOMER_CATEGORY);
        CUSTOMER.setCustomerRoles(CUSTOMER_ROLES);
        CUSTOMER.setCustomerName(CUSTOMER_NAME);
        CUSTOMER.setCustomerAge(CUSTOMER_AGE);
        CUSTOMER.setUsername(CUSTOMER_USERNAME);
        CUSTOMER.setPassword(CUSTOMER_PASSWORD);
    }

    @Test
    public void findCustomerAllTest() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(CUSTOMER));
        List<Customer> response = customerServiceImpl.findCustomerAll();

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }
    @Test
    public void getCustomerTest() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(CUSTOMER));
        Customer response = customerServiceImpl.getCustomer(CUSTOMER_ID);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH ID",response.getId(), CUSTOMER_ID);
    }
    @Test
    public void findByCustomerCategory() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.findByCustomerCategory(CUSTOMER_CATEGORY)).thenReturn(Arrays.asList(CUSTOMER));
        List<Customer> response = customerServiceImpl.findByCustomerCategory(CUSTOMER_CATEGORY);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }
    @Test
    public void findOneByUsername() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.findOneByUsername(CUSTOMER_USERNAME)).thenReturn(CUSTOMER);
        Customer response = customerServiceImpl.findOneByUsername(CUSTOMER_USERNAME);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH USERNAME",response.getUsername(),CUSTOMER_USERNAME);
    }
    @Test
    public void createCustomer() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(CUSTOMER);
        Customer response = customerServiceImpl.createCustomer(CUSTOMER);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_CREATED);
    }
    @Test
    public void updateCustomer() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(CUSTOMER);
        Mockito.when(customerRepository.findById(CUSTOMER_ID))
                .thenReturn(Optional.of(CUSTOMER));
        Customer response = customerServiceImpl.updateCustomer(CUSTOMER);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_UPDATED);
    }
    @Test
    public void deleteCustomer() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(CUSTOMER);
        Mockito.when(customerRepository.findById(CUSTOMER_ID))
                .thenReturn(Optional.of(CUSTOMER));
        Customer response = customerServiceImpl.deleteCustomer(CUSTOMER_ID);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_DELETED);
    }
    //Todo: loadUserByUsername and assignRole
}
