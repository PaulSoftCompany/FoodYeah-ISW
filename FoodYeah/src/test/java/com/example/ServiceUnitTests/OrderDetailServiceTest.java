package com.example.ServiceUnitTests;

import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.repository.OrderDetailRepository;
import com.example.service.impl.OrderDetailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class OrderDetailServiceTest {
    private static final OrderDetail ORDER_DETAIL = new OrderDetail();
    private static final Long ORDER_DETAIL_ID =1L;
    private static final Order ORDER = new Order();
    private static final Long ORDER_ID = 1L;
    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final byte QUANTITY = 5;
    private static final float UNITPRICE = 1.0f;
    private static final float TOTALPRICE = 5.0f;
    private static final String STATE_CREATED = "CREATED";
    private static final String STATE_UPDATED = "UPDATED";
    private static final String STATE_DELETED = "DELETED";
    @InjectMocks
    OrderDetailServiceImpl orderDetailService;
    @Mock
    OrderDetailRepository orderDetailRepository;
    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        ORDER_DETAIL.setId(ORDER_DETAIL_ID);
        ORDER.setId(ORDER_ID);
        ORDER_DETAIL.setOrder(ORDER);
        ORDER_DETAIL.setProduct(PRODUCT);
        PRODUCT.setId(PRODUCT_ID);
        ORDER_DETAIL.setQuantity(QUANTITY);
        ORDER_DETAIL.setUnitPrice(UNITPRICE);
        ORDER_DETAIL.setTotalPrice(TOTALPRICE);
    }
    @Test
    public void findOrderDetailAll() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(orderDetailRepository.findAll())
                .thenReturn(Arrays.asList(ORDER_DETAIL));
        List<OrderDetail> response = orderDetailService.findOrderDetailAll();

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertFalse(methodName + " - EMPTY TEST",response.isEmpty());
        Util.assertEquals(methodName + " - SIZE TEST",response.size(),1);
    }
    @Test
    public void getOrderDetail() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(orderDetailRepository.findById(ORDER_DETAIL_ID))
                .thenReturn(java.util.Optional.of(ORDER_DETAIL));
        OrderDetail response = orderDetailService.getOrderDetail(ORDER_DETAIL_ID);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH ID",response.getId(),ORDER_DETAIL_ID);
    }
    @Test
    public void createOrderDetail() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(orderDetailRepository.save(Mockito.any(OrderDetail.class)))
                .thenReturn(ORDER_DETAIL);
        OrderDetail response = orderDetailService.createOrderDetail(ORDER_DETAIL);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_CREATED);
    }
    @Test
    public void updateOrderDetailTest() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        Mockito.when(orderDetailRepository.save(Mockito.any(OrderDetail.class)))
                .thenReturn(ORDER_DETAIL);
        Mockito.when(orderDetailRepository.findById(ORDER_DETAIL_ID))
                .thenReturn(Optional.of(ORDER_DETAIL));
        OrderDetail response = orderDetailService.updateOrderDetail(ORDER_DETAIL);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_UPDATED);
    }
    @Test
    public void deleteOrderDetail() throws Exception{
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        Mockito.when(orderDetailRepository.save(Mockito.any(OrderDetail.class)))
                .thenReturn(ORDER_DETAIL);
        Mockito.when(orderDetailRepository.findById(ORDER_DETAIL_ID))
                .thenReturn(Optional.of(ORDER_DETAIL));
        OrderDetail response = orderDetailService.deleteOrderDetail(ORDER_DETAIL_ID);

        Util.assertNotNull(methodName + " - NULL TEST",response);
        Util.assertEquals(methodName + " - MATCH STATE TEST",response.getState(),STATE_DELETED);
    }
}
