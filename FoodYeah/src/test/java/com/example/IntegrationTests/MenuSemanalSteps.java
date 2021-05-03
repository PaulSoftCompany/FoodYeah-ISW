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

public class MenuSemanalSteps {

    private static final Product PRODUCT = new Product();

    private static List<Product> response;

    @Mock
    ProductRepository productRepository;


    @InjectMocks
    ProductServiceImpl productServiceImpl;



    @Given("Un usuario quiere visualizar el menu")
    public void unUsuarioQuiereVisualizarElMenu() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(productRepository.menuSemanal()).thenReturn(Arrays.asList(PRODUCT));
    }

    @When("Ingresa a la aplicacion")
    public void ingresaALaAplicacion() {
        response = productServiceImpl.menuSemanal();
    }

    @Then("Se muestra el menu semanal")
    public void seMuestraElMenuSemanal() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        assertNotNull(methodName + " - NULL TEST",response);
        assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }
}
