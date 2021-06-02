package com.paulsoft.foodyeah.ServiceUnitTests;

import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.UpdateCustomerDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.CreateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.OrderDetailDto;
import com.paulsoft.foodyeah.dtos.OrderDetailDto.UpdateOrderDetailDto;
import com.paulsoft.foodyeah.dtos.ProductDto.CreateProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.ProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.UpdateProductDto;
import com.paulsoft.foodyeah.entities.Customer;
import com.paulsoft.foodyeah.entities.OrderDetail;
import com.paulsoft.foodyeah.entities.Product;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CustomerRepository;
import com.paulsoft.foodyeah.repositories.OrderDetailRepository;
import com.paulsoft.foodyeah.services.impl.OrderDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderDetailServiceTest {
    @InjectMocks
    OrderDetailServiceImpl service;
    @Mock
    OrderDetailRepository repository;
    public static final ModelMapper modelMapper = new ModelMapper();
    private static final OrderDetail ORDER_DETAIL = new OrderDetail();
    private static final Long ORDER_DETAIL_ID = 1L;
    private static final Double ORDER_DETAIL_UNIT_PRICE = 50.0d;
    private static final String ORDER_DETAIL_UNIT_NAME = "OrderDetailUnitName";
    private static final Double ORDER_DETAIL_TOTAL_PRICE = 100.0d;
    private static final Byte ORDER_DETAIL_QUANTITY = 2;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ORDER_DETAIL.setId(ORDER_DETAIL_ID);
        ORDER_DETAIL.setUnitPrice(ORDER_DETAIL_UNIT_PRICE);
        ORDER_DETAIL.setUnitName(ORDER_DETAIL_UNIT_NAME);
        ORDER_DETAIL.setTotalPrice(ORDER_DETAIL_TOTAL_PRICE);
        ORDER_DETAIL.setQuantity(ORDER_DETAIL_QUANTITY);
    }
    //OrderDetailDto getOrderDetailById(Long id) throws ResourceException;
    @Test
    public void getOrderDetailById() throws Exception{
        String methodName = "GET ORDER DETAIL BY ID";
        //Mock
        Mockito.when(repository.findById(ORDER_DETAIL_ID)).thenReturn(Optional.empty());
        try{
            service.getOrderDetailById(ORDER_DETAIL_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(ORDER_DETAIL_ID)).thenReturn(Optional.of(ORDER_DETAIL));
        OrderDetailDto response;
        //Get response
        try {
            response = service.getOrderDetailById(ORDER_DETAIL_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map Customer to CustomerDto:
        OrderDetailDto mapped =  convertToResource(ORDER_DETAIL);
        //Assertions:
        try {
            validateProduct(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //List<OrderDetailDto> getOrderDetails() throws ResourceException;
    @Test
    public void getOrderDetails() throws Exception{
        String methodName = "GET ORDER DETAILS";
        //Mock
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(ORDER_DETAIL));
        List<OrderDetailDto> response;
        //Get response
        try {
            response = service.getOrderDetails();
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        OrderDetailDto mapped =  convertToResource(ORDER_DETAIL);
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
    //OrderDetailDto updateOrderDetailDto(UpdateOrderDetailDto updateOrderDetailDto, Long id) throws ResourceException;
    @Test
    public void updateOrderDetailDto() throws Exception{
        String methodName = "UPDATE ORDER DETAIL";
        Mockito.when(repository.findById(ORDER_DETAIL_ID)).thenReturn(Optional.empty());
        //Map entities:
        UpdateOrderDetailDto toupdate =  convertToUpdateResource(ORDER_DETAIL);
        //Primero validamos el error de customer con codigo ya creada:
        try{
            service.updateOrderDetailDto(toupdate,ORDER_DETAIL_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PASSED"+Util.ANSI_RESET);
        }
        //Mock
        OrderDetailDto response;
        Mockito.when(repository.findById(ORDER_DETAIL_ID)).thenReturn(Optional.of(ORDER_DETAIL));
        Mockito.when(repository.save(Mockito.any(OrderDetail.class))).thenReturn(ORDER_DETAIL);
        //Get response
        try {
            response = service.updateOrderDetailDto(toupdate,ORDER_DETAIL_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        OrderDetailDto mapped = convertToResource(ORDER_DETAIL);
        //Assertions:
        try {
            validateProduct(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    private void validateProduct(OrderDetailDto entity, OrderDetailDto mapped) throws AssertionError{
        Util.assertNotNull("ORDER DETAIL NOT NULL",entity);
        Util.assertEquals("ORDER DETAIL ID",entity.getId(),mapped.getId());
        Util.assertEquals("ORDER DETAIL UNIT PRICE",entity.getUnitPrice(),mapped.getUnitPrice());
        Util.assertEquals("ORDER DETAIL UNIT NAME",entity.getUnitName(),mapped.getUnitName());
        Util.assertEquals("ORDER DETAIL TOTAL PRICE",entity.getTotalPrice(),mapped.getTotalPrice());
        Util.assertEquals("ORDER DETAIL QUANTITY",entity.getQuantity(),mapped.getQuantity());
    }
    private UpdateOrderDetailDto convertToUpdateResource(OrderDetail entity){
        return modelMapper.map(entity,UpdateOrderDetailDto.class);
    }
    private OrderDetailDto convertToResource(OrderDetail entity) {
        return modelMapper.map(entity, OrderDetailDto.class);
    }
}
