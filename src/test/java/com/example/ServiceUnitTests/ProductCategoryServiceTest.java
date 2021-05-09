package com.example.ServiceUnitTests;

import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.repository.ProductCategoryRepository;
import com.example.repository.ProductRepository;
import com.example.service.ProductCategoryService;
import com.example.service.impl.ProductCategoryServiceImpl;
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

public class ProductCategoryServiceTest {

    @InjectMocks
    ProductCategoryServiceImpl productCategoryServiceServiceImpl;

    @Mock
    ProductCategoryRepository productCategoryRepository;

    private static  final ProductCategory PRODUCT_CATEGORY  = new ProductCategory();
    private static final Long PRODUCT_CATEGORYID = 1L;
    private static final String PRODUCT_CATEGORYNAME = "CategoriaNombre";
    private static final String PRODUCT_CATEGORYDESCRIPTION = "CategoriaDescripcion";
    private static final String STATE_CREATED = "CREATED";
    private static final String STATE_UPDATED = "UPDATED";
    private static final String STATE_DELETED = "DELETED";

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        PRODUCT_CATEGORY.setId(PRODUCT_CATEGORYID);
        PRODUCT_CATEGORY.setProductCategoryName(PRODUCT_CATEGORYNAME);
        PRODUCT_CATEGORY.setProductCategoryDescription(PRODUCT_CATEGORYDESCRIPTION);
    }

    @Test
    public void findProduct_CategoryAll() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productCategoryRepository.findAll()).thenReturn(Arrays.asList(PRODUCT_CATEGORY));
        List<ProductCategory> response = productCategoryServiceServiceImpl.findProduct_CategoryAll();

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }

    @Test
    public void getProduct_Category() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productCategoryRepository.findById(PRODUCT_CATEGORYID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        ProductCategory response = productCategoryServiceServiceImpl.getProduct_Category(PRODUCT_CATEGORYID);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH ID",response.getId(), PRODUCT_CATEGORYID);
    }

    @Test
    public void createProduct_Category() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productCategoryRepository.save(Mockito.any(ProductCategory.class))).thenReturn(PRODUCT_CATEGORY);
        ProductCategory response = productCategoryServiceServiceImpl.createProduct_Category(PRODUCT_CATEGORY);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH STATE",response.getState(), STATE_CREATED);
    }

    @Test
    public void deleteProductTest() throws  Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(productCategoryRepository.findById(PRODUCT_CATEGORYID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        Mockito.when(productCategoryRepository.save(Mockito.any(ProductCategory.class))).thenReturn(PRODUCT_CATEGORY);
        ProductCategory response = productCategoryServiceServiceImpl.deleteProduct_Category(PRODUCT_CATEGORYID);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH STATE",response.getState(), STATE_DELETED);
    }
    @Test
    public void updateProductTest() throws  Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        Mockito.when(productCategoryRepository.findById(PRODUCT_CATEGORYID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        Mockito.when(productCategoryRepository.save(Mockito.any(ProductCategory.class))).thenReturn(PRODUCT_CATEGORY);
        ProductCategory response = productCategoryServiceServiceImpl.updateProduct_Category(PRODUCT_CATEGORY);

        Util.assertNotNull(methodName + " - NULL TEST", response);
        Util.assertEquals(methodName + " - MATCH STATE",response.getState(), STATE_UPDATED);
    }
}
