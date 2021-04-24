package com.example.ServiceUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.entity.Card;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderDetail;
import com.example.entity.Product;
import com.example.repository.CardRepository;
import com.example.repository.OrderDetailRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.service.impl.OrderServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {
    /**
     * --Creacion de los datos de prueba para la orden--
     */
    /**
     * Creacion de la OrderDetail de la orden
     */
    private static final OrderDetail ORDER_DETAIL = new OrderDetail();
    private static final Long OD_ID = 1L;
    private static final Byte OD_QUANTITY = 69;
    private static final Float OD_UNIT_PRICE = 420F;
    private static final Float OD_TOTAL_PRICE = 69F * 420F;
    private static final String OD_STATE = "CREATED";

    /**
     * Creacion de la orden
     */
    private static final Order ORDER = new Order();
    private static final Long ORDER_ID = 1L;
    //la orden tiene un cliente
    private static final Customer CUSTOMER = new Customer();
    //La orden tiene un detalle de OrderDetails
    private static final List<OrderDetail> ORDER_DETAILS = Arrays.asList(ORDER_DETAIL);
    //Datos de la orden:
    private static final String DATE = "04-20-6969";
    private static final String INIT_TIME = "00:00:00";
    private static final String END_TIME = "00:04:20";
    private static final Float TOTAL_PRICE = 69F * 420F;
    private static final String STATE = "CREATED";
    /**
     * Creacion del product de la OrderDetail
     * En este caso el product no tiene product_category
     */
    private static final Product PRODUCT = new Product();
    private static final Long PRODUCT_ID = 1L;
    private static final Float PRODUCT_PRICE = 420F;
    private static final Integer STOCK = 420;
    /**
     * Creación de la tarjeta del cliente
     * con esta tarjeta se valida el saldo y se paga la orden
     */
    private static final Card CARD = new Card();
    private static final Long CARD_ID = 1L;
    private static final Float CARD_MONEY = 69F * 420F;
    
    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderDetailRepository orderDetailRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    CardRepository cardRepository;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);

        CARD.setId(CARD_ID);
        CARD.setCardMoney(CARD_MONEY);

        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setProductPrice(PRODUCT_PRICE);
        PRODUCT.setStock(STOCK);

        ORDER_DETAIL.setId(OD_ID);
        ORDER_DETAIL.setProduct(PRODUCT);
        ORDER_DETAIL.setQuantity(OD_QUANTITY);
        ORDER_DETAIL.setUnitPrice(OD_UNIT_PRICE);
        ORDER_DETAIL.setTotalPrice(OD_TOTAL_PRICE);
        ORDER_DETAIL.setState(OD_STATE);

        ORDER.setId(ORDER_ID);
        ORDER.setCostumer(CUSTOMER);
        ORDER.setOrderDetails(ORDER_DETAILS);
        ORDER.setDate(DATE);
        ORDER.setInittime(INIT_TIME);
        ORDER.setEndtime(END_TIME);
        ORDER.setTotalPrice(TOTAL_PRICE);
        ORDER.setState(STATE);
    }

    @Test
    public void findOrderAllTest() throws Exception {
        Mockito.when(orderRepository.findAll()).thenReturn(Arrays.asList(ORDER));
        List<Order> response = orderServiceImpl.findOrderAll();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(response.size(), 1);
    }

    @Test
    public void getOrderTest() throws Exception {
        Mockito.when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER));
        orderServiceImpl.getOrder(ORDER_ID);
    }

    @Test
    public void createOrderTest() throws Exception {
        Mockito.when(productRepository.getOne(PRODUCT_ID)).thenReturn(PRODUCT);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(ORDER);
        orderServiceImpl.createOrder(ORDER);
    }

    @Test
    public void SetEndTimeTest() throws Exception {
        orderServiceImpl.SetEndTime(ORDER);
        assertEquals(ORDER.getState(), "DELIVERED");
    }

    @Test
    public void DecreaseStockTest() throws Exception {
        orderServiceImpl.DecreaseStock(ORDER);
        assertEquals(ORDER.getOrderDetails().get(0).getProduct().getStock(), 351);
    }

    @Test
    public void GetAverageTimeTest() throws Exception {
        Mockito.when(orderRepository.findAll()).thenReturn(Arrays.asList(ORDER));
        String response = orderServiceImpl.GetAverageTime();
        assertEquals(response, "4");
    }

    @Test
    public void DecreaseCostumerMoneyTest() throws Exception {
        Mockito.when(cardRepository.getOne(CARD_ID)).thenReturn(CARD);
        Mockito.when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER));

        Boolean response = orderServiceImpl.DecreaseCostumerMoney(CARD_ID, ORDER_ID);
        assertEquals(CARD.getCardMoney(), 0F, 0F);
        assertEquals(response, true);
    }
}
