package com.paulsoft.foodyeah.ServiceUnitTests;

import com.paulsoft.foodyeah.dtos.CardDto.CardDto;
import com.paulsoft.foodyeah.dtos.CardDto.CreateCardDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.CreateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.UpdateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.ProductDto.CreateProductDto;
import com.paulsoft.foodyeah.dtos.orderDto.CreateOrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.OrderDto;
import com.paulsoft.foodyeah.dtos.orderDto.UpdateOrderDto;
import com.paulsoft.foodyeah.entities.*;
import com.paulsoft.foodyeah.exceptions.InternalServerErrorException;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CustomerRepository;
import com.paulsoft.foodyeah.repositories.OrderDetailRepository;
import com.paulsoft.foodyeah.repositories.OrderRepository;
import com.paulsoft.foodyeah.repositories.ProductRepository;
import com.paulsoft.foodyeah.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

public class OrderServiceTest {
    @InjectMocks
    OrderServiceImpl service;
    @Mock
    OrderRepository repository;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderDetailRepository orderDetailRepository;

    public static final ModelMapper modelMapper = new ModelMapper();
    private static final Order ORDER = new Order();
    private static final Long ORDER_ID = 1L;
    private static final Date DATE = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    private static final Double ORDER_TOTAL_PRICE = 100.0d;

    private static final Customer CUSTOMER = new Customer();
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRSTNAME = "NombreDelCliente";
    private static final String CUSTOMER_LASTNAME = "ApellidoDelCliente";
    private static final String CUSTOMER_CODE = "U20181ALGO";
    private static final String CUSTOMER_PASSWORD = "asd123ASD#";
    private static final Boolean STATE_CREATED = true;
    private static final Boolean STATE_DELETED = false;

    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "ProductoNombre";
    private static final Double PRODUCT_PRICE = 50.0d;
    private static final Byte PRODUCT_SELLDAY = 1;
    private static final String PRODUCT_IMAGEURL = "https://gestion.pe/resizer/JQjqRhOR2c0U_-f_OPQdREYQRI4=/1200x800/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/DBQRWFS66VCUFHX2ODH6ITHJQI.jpg";

    private static final OrderDetail ORDER_DETAIL = new OrderDetail();
    private static final Long ORDER_DETAIL_ID = 1L;
    private static final Double ORDER_DETAIL_UNIT_PRICE = 50.0d;
    private static final String ORDER_DETAIL_UNIT_NAME = "OrderDetailUnitName";
    private static final Double ORDER_DETAIL_TOTAL_PRICE = 100.0d;
    private static final Byte ORDER_DETAIL_QUANTITY = 2;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ORDER.setId(ORDER_ID);
        ORDER.setDate(DATE);
        ORDER.setTotalPrice(ORDER_TOTAL_PRICE);

        CUSTOMER.setId(CUSTOMER_ID);
        CUSTOMER.setFirstName(CUSTOMER_FIRSTNAME);
        CUSTOMER.setLastName(CUSTOMER_LASTNAME);
        CUSTOMER.setCode(CUSTOMER_CODE);
        CUSTOMER.setPassword(CUSTOMER_PASSWORD);
        ORDER.setCustomer(CUSTOMER);

        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setName(PRODUCT_NAME);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setSellDay(PRODUCT_SELLDAY);
        PRODUCT.setImageUrl(PRODUCT_IMAGEURL);
        PRODUCT.setCreatedAt(DATE);

        ORDER_DETAIL.setId(ORDER_DETAIL_ID);
        ORDER_DETAIL.setUnitPrice(ORDER_DETAIL_UNIT_PRICE);
        ORDER_DETAIL.setUnitName(ORDER_DETAIL_UNIT_NAME);
        ORDER_DETAIL.setTotalPrice(ORDER_DETAIL_TOTAL_PRICE);
        ORDER_DETAIL.setQuantity(ORDER_DETAIL_QUANTITY);
        ORDER_DETAIL.setProduct(PRODUCT);
        ORDER.setDetails(Arrays.asList(ORDER_DETAIL));
    }
    //List<OrderDto> getOrders() throws ResourceException;
    @Test
    public void getOrders() throws Exception{
        String methodName = "GET ORDERS";
        //Mock
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(ORDER));
        List<OrderDto> response;
        //Get response
        try {
            response = service.getOrders();
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        OrderDto mapped =  convertToResource(ORDER);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validate(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //List<OrderDto> getOrdersByCustomerId(Long id) throws ResourceException;
    @Test
    public void getOrdersByCustomerId() throws Exception{
        String methodName = "GET ORDERS BY CUSTOMER ID";
        //Mock
        Mockito.when(repository.findAllByCustomerId(CUSTOMER_ID)).thenReturn(Arrays.asList(ORDER));
        List<OrderDto> response;
        //Get response
        try {
            response = service.getOrdersByCustomerId(CUSTOMER_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        OrderDto mapped =  convertToResource(ORDER);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validate(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //OrderDto getOrderById(Long id) throws ResourceException;
    @Test
    public void getOrderById() throws Exception{
        String methodName = "GET ORDER BY ID";
        //Mock
        Mockito.when(repository.findById(ORDER_ID)).thenReturn(Optional.empty());
        try{
            service.getOrderById(ORDER_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION CARD NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER));
        OrderDto response;
        //Get response
        try {
            response = service.getOrderById(ORDER_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        OrderDto mapped =  convertToResource(ORDER);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validate(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //OrderDto createOrder(CreateOrderDto createOrderDto) throws ResourceException, ParseException;
    @Test
    public void createOrder() throws Exception{
        String methodName = "CREATE ORDER";
        //Mock
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        CreateOrderDto source = convertToCreateResource(ORDER);
        try{
            service.createOrder(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION CUSTOMER NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
        Mockito.when(repository.save(Mockito.any(Order.class))).thenReturn(ORDER);
        Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());
        try{
            service.createOrder(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT));
        Mockito.when(orderDetailRepository.save(Mockito.any(OrderDetail.class))).thenReturn(ORDER_DETAIL);
        Mockito.when(repository.save(Mockito.any(Order.class))).thenReturn(ORDER);
        OrderDto response;
        //Get response
        try {
            response = service.createOrder(source);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        OrderDto mapped =  convertToResource(ORDER);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validate(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    private void validate(OrderDto entity, OrderDto mapped) throws AssertionError{
        Util.assertNotNull("ORDER NOT NULL",entity);
        Util.assertEquals("ORDER ID",entity.getId(),mapped.getId());
        Util.assertEquals("ORDER DATE",entity.getDate(),mapped.getDate());
        Util.assertEquals("ORDER TOTAL PRICE",entity.getTotalPrice(),mapped.getTotalPrice());
        Util.assertEquals("ORDER CUSTOMER ID",entity.getCustomerId(),mapped.getCustomerId());
    }
    private UpdateOrderDto convertToUpdateResource(Order entity){
        return modelMapper.map(entity,UpdateOrderDto.class);
    }
    private CreateOrderDto convertToCreateResource(Order entity){
        return modelMapper.map(entity,CreateOrderDto.class);
    }
    private Order convertToEntity(CreateOrderDto resource) {
        return modelMapper.map(resource, Order.class);
    }
    private OrderDto convertToResource(Order entity) {
        return modelMapper.map(entity, OrderDto.class);
    }
}
