package com.example.ServiceUnitTests;

import com.example.entity.Order;
import com.example.entity.Product;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductServiceTest {



    /* Creacion del producto
  */
    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final Float PRODUCT_PRICE = 420F;
    private static final Integer STOCK = 420;
    private static final Byte SELLDAY = 1;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setStock(STOCK);
        PRODUCT.setSellday(SELLDAY);
        PRODUCT.setState("CREATED");
    }


    @Test
    public void findProductAllTest() throws Exception {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(PRODUCT));
        List<Product> response = productServiceImpl.findProductAll();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test
    public void getProductTest() throws Exception {
        Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Product Response = productServiceImpl.getProduct(PRODUCT_ID);
    }

    @Test
    public void createProductTest() throws Exception {
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(PRODUCT);
        productServiceImpl.createProduct(PRODUCT);
    }
    @Test
    public void findProductBySellDayTest() throws Exception {
        Mockito.when(productRepository.findBySellday(SELLDAY)).thenReturn(Arrays.asList(PRODUCT));
        List<Product> response = productServiceImpl.findBySellday(SELLDAY);
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test
    public void findMenuSemanalTest() throws  Exception{
        Mockito.when(productRepository.menuSemanal()).thenReturn(Arrays.asList(PRODUCT));
        List<Product> response = productServiceImpl.menuSemanal();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test
    public void deleteProductTest() throws  Exception{
        Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        productServiceImpl.deleteProduct(PRODUCT_ID);
    }
    @Test
    public void updateProductTest() throws  Exception{
        Product edit = PRODUCT;
        Product response = productServiceImpl.updateProduct(edit);
    }
}
