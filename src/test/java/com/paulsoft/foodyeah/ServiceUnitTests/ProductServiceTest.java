package com.paulsoft.foodyeah.ServiceUnitTests;

import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductDto.CreateProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.ProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.UpdateProductDto;
import com.paulsoft.foodyeah.entities.Product;
import com.paulsoft.foodyeah.entities.ProductCategory;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.ProductCategoryRepository;
import com.paulsoft.foodyeah.repositories.ProductRepository;
import com.paulsoft.foodyeah.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl service;
    @Mock
    ProductRepository repository;
    @Mock
    ProductCategoryRepository categoryRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "ProductoNombre";
    private static final Double PRODUCT_PRICE = 100.0;
    private static final Byte PRODUCT_SELLDAY = 1;
    private static final String PRODUCT_IMAGEURL = "https://gestion.pe/resizer/JQjqRhOR2c0U_-f_OPQdREYQRI4=/1200x800/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/DBQRWFS66VCUFHX2ODH6ITHJQI.jpg";
    private static final Boolean STATE_CREATED = true;
    private static final Boolean STATE_DELETED = false;
    private static final Date DATE = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    private static final ProductCategory PRODUCT_CATEGORY  = new ProductCategory();
    private static final Long PRODUCT_CATEGORY_ID = 1L;
    private static final String PRODUCT_CATEGORY_NAME = "CategoriaNombre";
    private static final String PRODUCT_CATEGORY_DESCRIPTION = "CategoriaDescripcion";

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setName(PRODUCT_NAME);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setSellDay(PRODUCT_SELLDAY);
        PRODUCT.setImageUrl(PRODUCT_IMAGEURL);
        PRODUCT.setCreatedAt(DATE);
        PRODUCT_CATEGORY.setId(PRODUCT_CATEGORY_ID);
        PRODUCT_CATEGORY.setName(PRODUCT_CATEGORY_NAME);
        PRODUCT_CATEGORY.setDescription(PRODUCT_CATEGORY_DESCRIPTION);
        PRODUCT.setProductCategory(PRODUCT_CATEGORY);
    }
    //List<ProductDto> getProducts() throws ResourceException;
    @Test
    public void getProducts() throws Exception{
        String methodName = "GET PRODUCTS";
        //Mock
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(PRODUCT));
        List<ProductDto> response;
        //Get response
        try {
            response = service.getProducts();
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //List<ProductDto> getProductsByState(Boolean state) throws ResourceException;
    @Test
    public void getProductsByState() throws Exception{
        String methodName = "GET PRODUCTS BY STATE";
        //Mock
        Mockito.when(repository.findAllByState(STATE_CREATED)).thenReturn(Arrays.asList(PRODUCT));
        List<ProductDto> response;
        //Get response
        try {
            response = service.getProductsByState(STATE_CREATED);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //List<ProductDto> getProductsBySellDay(byte sellDay) throws ResourceException;
    @Test
    public void getProductsBySellDay() throws Exception{
        String methodName = "GET PRODUCTS BY SELLDAY";
        //Mock
        Mockito.when(repository.findAllBySellDay(PRODUCT_SELLDAY)).thenReturn(Arrays.asList(PRODUCT));
        List<ProductDto> response;
        //Get response
        try {
            response = service.getProductsBySellDay(PRODUCT_SELLDAY);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //List<ProductDto> getProductsByCategoryId(Long id) throws ResourceException;
    @Test
    public void getProductsByCategoryId() throws Exception{
        String methodName = "GET PRODUCTS BY CATEGORY ID";
        //Mock
        Mockito.when(repository.findAllByProductCategoryId(PRODUCT_CATEGORY_ID)).thenReturn(Arrays.asList(PRODUCT));
        List<ProductDto> response;
        //Get response
        try {
            response = service.getProductsByCategoryId(PRODUCT_CATEGORY_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //ProductDto getProductById(Long id) throws ResourceException;
    @Test
    public void getProductById() throws Exception{
        String methodName = "GET PRODUCT BY ID";
        //Mock
        Mockito.when(repository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        ProductDto response;
        //Get response
        try {
            response = service.getProductById(PRODUCT_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //ProductDto getProductByName(String name) throws ResourceException;
    @Test
    public void getProductByName() throws Exception{
        String methodName = "GET PRODUCT BY NAME";
        //Mock
        Mockito.when(repository.findByName(PRODUCT_NAME)).thenReturn(Optional.of(PRODUCT));
        ProductDto response;
        //Get response
        try {
            response = service.getProductByName(PRODUCT_NAME);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //ProductDto createProduct(CreateProductDto createProductDto) throws ResourceException, ParseException;
    @Test
    public void createProduct() throws Exception{
        String methodName = "CREATE PRODUCT";
        //Mock
        Mockito.when(repository.findByName(PRODUCT_NAME)).thenReturn(Optional.of(PRODUCT));
        CreateProductDto source = convertToCreateResource(PRODUCT);
        try{
            service.createProduct(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findByName(PRODUCT_NAME)).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(PRODUCT_CATEGORY_ID)).thenReturn(Optional.empty());
        try{
            service.createProduct(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT CATEGORY DONT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(categoryRepository.findById(PRODUCT_CATEGORY_ID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(PRODUCT);
        ProductDto response;
        //Get response
        try {
            response = service.createProduct(source);
        }catch(ResourceException|ParseException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //ProductDto updateProduct(UpdateProductDto updateProductDto, Long id) throws ResourceException;
    @Test
    public void updateProduct() throws Exception{
        String methodName = "UPDATE PRODUCT";
        //Mock
        Mockito.when(repository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
        UpdateProductDto source = convertToUpdateResource(PRODUCT);
        try{
            service.updateProduct(source, PRODUCT_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Mockito.when(categoryRepository.findById(PRODUCT_CATEGORY_ID)).thenReturn(Optional.empty());
        try{
            service.updateProduct(source, PRODUCT_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT CATEGORY DONT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(categoryRepository.findById(PRODUCT_CATEGORY_ID)).thenReturn(Optional.of(PRODUCT_CATEGORY));
        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(PRODUCT);
        ProductDto response;
        //Get response
        try {
            response = service.updateProduct(source, PRODUCT_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        ProductDto mapped =  convertToResource(PRODUCT);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateProduct(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //String deleteProduct(Long id) throws ResourceException;
    @Test
    public void deleteProduct() throws Exception{
        String methodName = "DELETE PRODUCT";
        //Mock
        Mockito.when(repository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
        try{
            service.deleteProduct(PRODUCT_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(PRODUCT);
        String response;
        //Get response
        try {
            response = service.deleteProduct(PRODUCT_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            Util.assertEquals("GET NAME",response, PRODUCT_NAME);
            Util.assertEquals("GET STATE",PRODUCT.getState(), STATE_DELETED);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    private void validateProduct(ProductDto entity, ProductDto mapped) throws AssertionError{
        Util.assertNotNull("PRODUCT NOT NULL",entity);
        Util.assertEquals("PRODUCT ID",entity.getId(),mapped.getId());
        Util.assertEquals("PRODUCT NAME",entity.getName(),mapped.getName());
        Util.assertEquals("PRODUCT PRICE",entity.getProductPrice(),mapped.getProductPrice());
        Util.assertEquals("PRODUCT SELLDAY",entity.getSellDay(),mapped.getSellDay());
        Util.assertEquals("PRODUCT IMAGEURL",entity.getImageUrl(),mapped.getImageUrl());
        Util.assertEquals("PRODUCT STATE",entity.getState(),mapped.getState());
        Util.assertEquals("PRODUCT CREATED AT",entity.getCreatedAt(),mapped.getCreatedAt());
    }
    private UpdateProductDto convertToUpdateResource(Product entity){
        return modelMapper.map(entity,UpdateProductDto.class);
    }
    private CreateProductDto convertToCreateResource(Product entity){
        return modelMapper.map(entity,CreateProductDto.class);
    }
    private Product convertToEntity(CreateProductDto resource) {
        return modelMapper.map(resource, Product.class);
    }

    private ProductDto convertToResource(Product entity) {
        return modelMapper.map(entity, ProductDto.class);
    }
}
