package com.example.IntegrationTests;

import com.example.ServiceUnitTests.Util;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import com.example.service.impl.ProductServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.example.ServiceUnitTests.Util.*;

public class S01Steps {

    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final Float PRODUCT_PRICE = 420F;
    private static final Integer STOCK = 420;
    private static final Byte SELLDAY = 1;
    private static final String PRODUCT_NAME = "GA";
    private static List<Product> response;

    @Mock
    ProductRepository productRepository;


    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Given("a user1")
    public void aUser1() {
        MockitoAnnotations.initMocks(this);
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setStock(STOCK);
        PRODUCT.setSellday(SELLDAY);
        PRODUCT.setProductName(PRODUCT_NAME);
        Mockito.when(productRepository.menuSemanal()).thenReturn(Arrays.asList(PRODUCT));
    }

    @When("loads the weekly menu")
    public void loadsTheWeeklyMenu() {
        response = productServiceImpl.menuSemanal();
    }

    @Then("the application shows the description of each dish")
    public void theApplicationShowsTheDescriptionOfEachDish() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        assertNotNull(methodName + " - NULL TEST",response);
        assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        assertEquals(methodName + " - SIZE TEST",response.size(),1);
        assertEquals(methodName + " - NAME TEST",response.get(0).getProductName(),PRODUCT_NAME);
        assertEquals(methodName + " - ID TEST",response.get(0).getId(),PRODUCT_ID);
        assertEquals(methodName + " - STOCK TEST",response.get(0).getStock(),STOCK);
        assertEquals(methodName + " - SELLDAY TEST",response.get(0).getSellday(),SELLDAY);
        assertEquals(methodName + " - PRICE TEST",response.get(0).getProductPrice(),PRODUCT_PRICE);
    }

    @Then("the application has to show the dishes by dates")
    public void theApplicationHasToShowTheDishesByDates() throws Exception  {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        assertNotNull(methodName + " - NULL TEST",response);
        assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        assertEquals(methodName + " - SIZE TEST",response.size(),1);
        assertEquals(methodName + " - SELLDAY TEST",response.get(0).getSellday(),SELLDAY);
    }

    @Then("the application refresh the weekly menu")
    public void theApplicationRefreshTheWeeklyMenu() throws Exception  {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        assertNotNull(methodName + " - NULL TEST",response);
        assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        assertEquals(methodName + " - SIZE TEST",response.size(),1);
        assertEquals(methodName + " - SELLDAY TEST",response.get(0).getSellday(),SELLDAY);
    }

    @Then("the system shows the dishes names")
    public void theSystemShowsTheDishesNames() throws Exception  {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        assertNotNull(methodName + " - NULL TEST",response);
        assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        assertEquals(methodName + " - SIZE TEST",response.size(),1);
        assertEquals(methodName + " - NAME TEST",response.get(0).getProductName(),PRODUCT_NAME);
    }
}
