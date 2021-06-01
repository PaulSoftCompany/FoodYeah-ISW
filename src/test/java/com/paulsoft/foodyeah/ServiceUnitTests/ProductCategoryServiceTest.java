package com.paulsoft.foodyeah.ServiceUnitTests;

import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.entities.Product;
import com.paulsoft.foodyeah.entities.ProductCategory;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.ProductCategoryRepository;
import com.paulsoft.foodyeah.services.impl.ProductCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

class ProductCategoryServiceTest {
    @InjectMocks
    ProductCategoryServiceImpl service;
    @Mock
    ProductCategoryRepository repository;

    public static final ModelMapper modelMapper = new ModelMapper();

    private static final ProductCategory PRODUCT_CATEGORY  = new ProductCategory();
    private static final Long ID = 1L;
    private static final String PRODUCT_CATEGORY_NAME = "CategoriaNombre";
    private static final String PRODUCT_CATEGORY_DESCRIPTION = "CategoriaDescripcion";
    private static final Boolean STATE_CREATED = true;
    private static final Boolean STATE_DELETED = false;
    private static final Date DATE = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "ProductoNombre";
    private static final Double PRODUCT_PRICE = 100.0;
    private static final Byte PRODUCT_SELLDAY = 1;
    private static final String PRODUCT_IMAGEURL = "https://gestion.pe/resizer/JQjqRhOR2c0U_-f_OPQdREYQRI4=/1200x800/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/DBQRWFS66VCUFHX2ODH6ITHJQI.jpg";

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        PRODUCT_CATEGORY.setId(ID);
        PRODUCT_CATEGORY.setName(PRODUCT_CATEGORY_NAME);
        PRODUCT_CATEGORY.setDescription(PRODUCT_CATEGORY_DESCRIPTION);
        PRODUCT_CATEGORY.setCreatedAt(DATE);
        PRODUCT_CATEGORY.setState(STATE_CREATED);
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setName(PRODUCT_NAME);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setSellDay(PRODUCT_SELLDAY);
        PRODUCT.setImageUrl(PRODUCT_IMAGEURL);
        PRODUCT.setState(STATE_CREATED);
        PRODUCT.setCreatedAt(DATE);
        PRODUCT.setProductCategory(PRODUCT_CATEGORY);
        PRODUCT_CATEGORY.setProducts(Arrays.asList(PRODUCT));
    }

    @Test
    public void getProductCategoryById() throws Exception{
        String methodName = "GET PRODUCT CATEGORY BY ID";
        //Mock
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        ProductCategoryDto response;
        //Get response
        try {
            response = service.getProductCategoryById(ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductCategoryDto mapped =  convertToResource(PRODUCT_CATEGORY);
        //Assertions:
        try {
            validateProductCategory(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }

    @Test
    void getProductCategories() throws Exception{
        String methodName = "GET PRODUCT CATEGORIES";
        //Mock
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(PRODUCT_CATEGORY));
        List<ProductCategoryDto> response;
        //Get response
        try {
            response = service.getProductCategories();
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductCategoryDto mapped =  convertToResource(PRODUCT_CATEGORY);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProductCategory(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    @Test
    public void updateProductCategory() throws Exception{
        String methodName = "UPDATE PRODUCT CATEGORY";
        //Mock
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        Mockito.when(repository.save(PRODUCT_CATEGORY)).thenReturn(PRODUCT_CATEGORY);
        UpdateProductCategoryDto toupdate =  convertToUpdateResource(PRODUCT_CATEGORY);
        ProductCategoryDto response;
        //Get response
        try {
            response = service.updateProductCategory(toupdate,ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductCategoryDto mapped = convertToResource(PRODUCT_CATEGORY);
        //Assertions:
        try {
            validateProductCategory(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    @Test
    void createProductCategory() throws Exception {
        String methodName = "CREATE PRODUCT CATEGORY";
        //Mock
        Mockito.when(repository.findByName(PRODUCT_CATEGORY_NAME)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        //Map entities:
        CreateProductCategoryDto source = convertToCreateResource(PRODUCT_CATEGORY);
        //Primero validamos el error de categoria con nombre ya creada:
        try{
            service.createProductCategory(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findByName(PRODUCT_CATEGORY_NAME)).thenReturn(Optional.empty());
        Mockito.when(repository.save(PRODUCT_CATEGORY)).thenReturn(PRODUCT_CATEGORY);
        ProductCategoryDto response;
        //Get response
        try {
            response = service.createProductCategory(source);
        }catch(ResourceException|ParseException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductCategoryDto mapped = convertToResource(PRODUCT_CATEGORY);
        //Assertions:
        try {
            validateProductCategory(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    /*
    String deleteProductCategory(Long id) throws ResourceException;
     */
    private void validateProductCategory(ProductCategoryDto entity, ProductCategoryDto mapped) throws AssertionError{
        Util.assertNotNull("PRODUCT CATEGORY NOT NULL",entity);
        Util.assertEquals("PRODUCT CATEGORY ID",entity.getId(),mapped.getId());
        Util.assertEquals("PRODUCT CATEGORY NAME",entity.getName(),mapped.getName());
        Util.assertEquals("PRODUCT CATEGORY DESCRIPTION",entity.getDescription(),mapped.getDescription());
        Util.assertEquals("PRODUCT CATEGORY DATE",entity.getCreatedAt(),mapped.getCreatedAt());
        Util.assertEquals("PRODUCT CATEGORY STATE",entity.getState(),mapped.getState());
        Util.assertNotNull("PRODUCTS NOT NULL", entity.getProducts());
        Util.assertEquals("PRODUCT ID",entity.getProducts().get(0).getId(),mapped.getProducts().get(0).getId());
        Util.assertEquals("PRODUCT NAME",entity.getProducts().get(0).getName(),mapped.getProducts().get(0).getName());
        Util.assertEquals("PRODUCT PRICE",entity.getProducts().get(0).getProductPrice(),mapped.getProducts().get(0).getProductPrice());
        Util.assertEquals("PRODUCT SELLDAY",entity.getProducts().get(0).getSellDay(),mapped.getProducts().get(0).getSellDay());
        Util.assertEquals("PRODUCT IMAGEURL",entity.getProducts().get(0).getImageUrl(),mapped.getProducts().get(0).getImageUrl());
        Util.assertEquals("PRODUCT STATE",entity.getProducts().get(0).getState(),mapped.getProducts().get(0).getState());
        Util.assertEquals("PRODUCT DATE",entity.getProducts().get(0).getCreatedAt(),mapped.getProducts().get(0).getCreatedAt());
        Util.assertEquals("PRODUCT PRODUCT CATEGORY ID",entity.getProducts().get(0).getProductCategoryId(),mapped.getProducts().get(0).getProductCategoryId());
    }
    private ProductCategory convertToEntity(CreateProductCategoryDto resource){
        return modelMapper.map(resource,ProductCategory.class);
    }
    private CreateProductCategoryDto convertToCreateResource(ProductCategory entity){
        return modelMapper.map(entity,CreateProductCategoryDto.class);
    }
    private UpdateProductCategoryDto convertToUpdateResource(ProductCategory entity){
        return modelMapper.map(entity,UpdateProductCategoryDto.class);
    }
    private ProductCategoryDto convertToResource(ProductCategory entity){
        return modelMapper.map(entity,ProductCategoryDto.class);
    }
}
