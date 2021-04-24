package com.example.ServiceUnitTests;

import com.example.repository.CustomerRepository;
import com.example.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {
    @InjectMocks
    CustomerServiceImpl orderServiceImpl;
    @Mock
    CustomerRepository customerRepository;
    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);

    }
}
