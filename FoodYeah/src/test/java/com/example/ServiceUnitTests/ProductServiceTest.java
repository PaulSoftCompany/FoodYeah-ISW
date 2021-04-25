package com.example.ServiceUnitTests;

import com.example.entity.Customer;
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
    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final Float PRODUCT_PRICE = 420F;
    private static final Integer STOCK = 420;
    private static final Byte SELLDAY = 1;

    private static final String STATE_CREATED = "CREATED";
    private static final String STATE_UPDATED = "UPDATED";
    private static final String STATE_DELETED = "DELETE";
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
    public void findProductAll() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(PRODUCT));
        List<Product> response = productServiceImpl.findProductAll();

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }

    @Test
    public void getProduct() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Product response = productServiceImpl.getProduct(PRODUCT_ID);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH ID",response.getId(), PRODUCT_ID);
    }

    @Test
    public void createProduct() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(PRODUCT);
        Product response = productServiceImpl.createProduct(PRODUCT);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_CREATED);
    }
    @Test
    public void findProductBySellDay() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.findBySellday(SELLDAY)).thenReturn(Arrays.asList(PRODUCT));
        List<Product> response = productServiceImpl.findBySellday(SELLDAY);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }

    @Test
    public void findMenuSemanal() throws  Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.menuSemanal()).thenReturn(Arrays.asList(PRODUCT));
        List<Product> response = productServiceImpl.menuSemanal();

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }

    @Test
    public void deleteProduct() throws  Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(PRODUCT);
        Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Product response = productServiceImpl.deleteProduct(PRODUCT_ID);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_DELETED);
    }
    @Test
    public void updateProduct() throws  Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(PRODUCT);
        Mockito.when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.of(PRODUCT));
        Product response = productServiceImpl.updateProduct(PRODUCT);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_UPDATED);
    }
}
