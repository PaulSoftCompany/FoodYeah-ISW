package com.paulsoft.foodyeah.BDDTests.steps;

/*import com.example.ServiceUnitTests.Util;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import com.example.service.impl.ProductServiceImpl;*/
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

//import static com.example.ServiceUnitTests.Util.*;

public class S02Steps {
/*    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final Float PRODUCT_PRICE = 420F;
    private static final Integer STOCK = 420;
    private static final Byte SELLDAY = 1;
    private static final String PRODUCT_NAME = "GA";
    private static List<Product> response;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productServiceImpl;*/
    @Given("a user2")
    public void aUser2() {
        //TODO
        /*MockitoAnnotations.initMocks(this);
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setStock(STOCK);
        PRODUCT.setSellday(SELLDAY);
        PRODUCT.setProductName(PRODUCT_NAME);
        Mockito.when(productRepository.platosALaCarta()).thenReturn(Arrays.asList(PRODUCT));*/
    }
    @When("loads the dishes a la carte")
    public void loadsTheDishesALaCarte() {
        //TODO
        //response = productServiceImpl.platosALaCarta();
    }

    @Then("the system shows the description of each dish")
    public void theSystemShowsTheDescriptionOfEachDish() throws Exception {
        //TODO
        /*String methodName = new Object() {}
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
        assertEquals(methodName + " - PRICE TEST",response.get(0).getProductPrice(),PRODUCT_PRICE);*/
    }

    @Then("the system shows the description of the filtered dish")
    public void theSystemShowsTheDescriptionOfTheFilteredDish() throws Exception {
        //TODO
        /*String methodName = new Object() {}
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
        assertEquals(methodName + " - PRICE TEST",response.get(0).getProductPrice(),PRODUCT_PRICE);*/
    }

    @Then("the system shows the description of each product")
    public void theSystemShowsTheDescriptionOfEachProduct() throws Exception {
        //TODO
        /*String methodName = new Object() {}
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
        assertEquals(methodName + " - PRICE TEST",response.get(0).getProductPrice(),PRODUCT_PRICE);*/
    }

    @Then("the system shows the product but without image")
    public void theSystemShowsTheProductButWithoutImage() throws Exception {
        //TODO
            /*String methodName = new Object() {}
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
        assertEquals(methodName + " - PRICE TEST",response.get(0).getProductPrice(),PRODUCT_PRICE);*/
    }
}
