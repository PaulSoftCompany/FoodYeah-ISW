package com.example.ServiceUnitTests;

import com.example.entity.CustomerCategory;
import com.example.entity.OrderDetail;
import com.example.repository.CustomerCategoryRepository;
import com.example.service.impl.CustomerCategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerCategoryServiceTest {
    private static final CustomerCategory CUSTOMER_CATEGORY = new CustomerCategory();
    private static final Long CUSTOMER_CATEGORY_ID = 1L;
    private static final String CUSTOMER_CATEGORY_NAME = "ALUMNO";
    private static final String CUSTOMER_CATEGORY_DESCRIPTION = "ALUMNO DE LA UPC";
    private static final String STATE_CREATED = "CREATED";
    private static final String STATE_UPDATED = "UPDATED";
    private static final String STATE_DELETED = "DELETED";

    @InjectMocks
    CustomerCategoryServiceImpl customerCategoryService;
    @Mock
    CustomerCategoryRepository customerCategoryRepository;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        CUSTOMER_CATEGORY.setId(CUSTOMER_CATEGORY_ID);
        CUSTOMER_CATEGORY.setCustomerCategoryName(CUSTOMER_CATEGORY_NAME);
        CUSTOMER_CATEGORY.setCustomerCategoryDescription(CUSTOMER_CATEGORY_DESCRIPTION);
    }

    @Test
    public void findCustomerCategoryAll() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerCategoryRepository.findAll())
                .thenReturn(Arrays.asList(CUSTOMER_CATEGORY));
        List<CustomerCategory> response = customerCategoryService.findCustomerCategoryAll();

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertFalse(methodName + " - EMPTY TEST", response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST", response.size(), 1);
    }

    @Test
    public void getCustomerCategory() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerCategoryRepository.findById(CUSTOMER_CATEGORY_ID))
                .thenReturn(Optional.of(CUSTOMER_CATEGORY));
        CustomerCategory response = customerCategoryService.getCustomerCategory(CUSTOMER_CATEGORY_ID);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH ID", response.getId(), CUSTOMER_CATEGORY_ID);
        /*Util.assertFalse(methodName + " - DONT MATCH ID", response.getId().equals(2L));*/
    }

    @Test
    public void createCustomerCategory() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerCategoryRepository.save(Mockito.any(CustomerCategory.class)))
                .thenReturn(CUSTOMER_CATEGORY);
        CustomerCategory response = customerCategoryService.createCustomerCategory(CUSTOMER_CATEGORY);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - STATE MATCH TEST", response.getState(), STATE_CREATED);
    }

    @Test
    public void updateCustomerCategory() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerCategoryRepository.save(Mockito.any(CustomerCategory.class)))
                .thenReturn(CUSTOMER_CATEGORY);
        Mockito.when(customerCategoryRepository.findById(CUSTOMER_CATEGORY_ID))
                .thenReturn(Optional.of(CUSTOMER_CATEGORY));
        CustomerCategory response = customerCategoryService.updateCustomerCategory(CUSTOMER_CATEGORY);

        Util.assertNotNull(methodName+" - NULL TEST",response);
        Util.assertEquals(methodName+" - STATE MATCH TEST", response.getState(),STATE_UPDATED);
    }

    @Test
    public void deleteCustomerCategory() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(customerCategoryRepository.save(Mockito.any(CustomerCategory.class)))
                .thenReturn(CUSTOMER_CATEGORY);
        Mockito.when(customerCategoryRepository.findById(CUSTOMER_CATEGORY_ID))
                .thenReturn(Optional.of(CUSTOMER_CATEGORY));
        CustomerCategory response = customerCategoryService.deleteCustomerCategory(CUSTOMER_CATEGORY_ID);

        Util.assertNotNull(methodName+" - NULL TEST",response);
        Util.assertEquals(methodName+" - STATE MATCH TEST", response.getState(),STATE_DELETED);
    }
}
