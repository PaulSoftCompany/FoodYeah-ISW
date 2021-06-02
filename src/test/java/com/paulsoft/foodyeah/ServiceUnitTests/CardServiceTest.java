package com.paulsoft.foodyeah.ServiceUnitTests;

import com.paulsoft.foodyeah.dtos.CardDto.CardDto;
import com.paulsoft.foodyeah.dtos.CardDto.CreateCardDto;
import com.paulsoft.foodyeah.dtos.CardDto.UpdateCardDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.CreateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.ProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductCategoryDto.UpdateProductCategoryDto;
import com.paulsoft.foodyeah.dtos.ProductDto.CreateProductDto;
import com.paulsoft.foodyeah.dtos.ProductDto.ProductDto;
import com.paulsoft.foodyeah.entities.Card;
import com.paulsoft.foodyeah.entities.Customer;
import com.paulsoft.foodyeah.entities.Product;
import com.paulsoft.foodyeah.entities.ProductCategory;
import com.paulsoft.foodyeah.exceptions.InternalServerErrorException;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CardRepository;
import com.paulsoft.foodyeah.repositories.CustomerRepository;
import com.paulsoft.foodyeah.services.impl.CardServiceImpl;
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

class CardServiceTest {
    @InjectMocks
    CardServiceImpl service;
    @Mock
    CardRepository repository;
    @Mock
    CustomerRepository customerRepository;
    public static final ModelMapper modelMapper = new ModelMapper();
    public static final Card CARD = new Card();
    public static final Long CARD_ID = 1L;
    public static final Boolean CARD_TYPE = true;
    public static final String CARD_OWNER = "CardOwner";
    public static final Long CARD_NUMBER = 1L;
    public static final Date CARD_EXPIREDATE = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    public static final Date DATE = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    public static final Double CARD_MONEY = 500.0d;
    private static final Customer CUSTOMER = new Customer();
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRSTNAME = "NombreDelCliente";
    private static final String CUSTOMER_LASTNAME = "ApellidoDelCliente";
    private static final String CUSTOMER_CODE = "U20181ALGO";
    private static final String CUSTOMER_PASSWORD = "asd123ASD#";
    private static final Boolean STATE_CREATED = true;
    private static final Boolean STATE_DELETED = false;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        CARD.setId(CARD_ID);
        CARD.setType(CARD_TYPE);
        CARD.setOwner(CARD_OWNER);
        CARD.setCardNumber(CARD_NUMBER);
        CARD.setExpireDate(CARD_EXPIREDATE);
        CARD.setCardMoney(CARD_MONEY);
        CUSTOMER.setId(CUSTOMER_ID);
        CUSTOMER.setFirstName(CUSTOMER_FIRSTNAME);
        CUSTOMER.setLastName(CUSTOMER_LASTNAME);
        CUSTOMER.setCode(CUSTOMER_CODE);
        CUSTOMER.setPassword(CUSTOMER_PASSWORD);
        CARD.setCustomer(CUSTOMER);
    }
    //List<CardDto> getCards() throws ResourceException;
    @Test
    void getCards() throws Exception{
        String methodName = "GET CARDS";
        //Mock
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(CARD));
        List<CardDto> response;
        //Get response
        try {
            response = service.getCards();
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        CardDto mapped =  convertToResource(CARD);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateCard(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //CardDto getCardById(Long id) throws ResourceException;
    @Test
    public void getCardById() throws Exception{
        String methodName = "GET CARD BY ID";
        //Mock
        Mockito.when(repository.findById(CARD_ID)).thenReturn(Optional.empty());
        try{
            service.getCardById(CARD_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION CARD NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(CARD_ID)).thenReturn(Optional.of(CARD));
        CardDto response;
        //Get response
        try {
            response = service.getCardById(CARD_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        CardDto mapped =  convertToResource(CARD);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateCard(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //List<CardDto> getAllByCustomerId(long id) throws ResourceException;
    @Test
    public void getAllByCustomerId() throws Exception{
        String methodName = "GET CARD BY ID";
        //Mock
        Mockito.when(repository.findAllByCustomerId(CUSTOMER_ID)).thenReturn(Arrays.asList(CARD));
        List<CardDto> response;
        //Get response
        try {
            response = service.getAllByCustomerId(CUSTOMER_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        CardDto mapped =  convertToResource(CARD);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateCard(response.get(0), mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //CardDto createCard(CreateCardDto createCardDto) throws ResourceException;
    @Test
    public void createCard() throws Exception{
        String methodName = "CREATE CARD";
        //Mock
        Mockito.when(repository.findCardByCardNumber(CARD_NUMBER)).thenReturn(Optional.of(CARD));
        CreateCardDto source = convertToCreateResource(CARD);
        try{
            service.createCard(source);
        }catch (InternalServerErrorException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION CARD FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findCardByCardNumber(CARD_NUMBER)).thenReturn(Optional.empty());
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        try{
            service.createCard(source);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION CUSTOMER DONT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(CUSTOMER));
        Mockito.when(repository.save(Mockito.any(Card.class))).thenReturn(CARD);
        CardDto response;
        //Get response
        try {
            response = service.createCard(source);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Map ProductCategory to ProductCategoryDto:
        CardDto mapped =  convertToResource(CARD);
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            validateCard(response, mapped);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    //String deleteCard(Long id) throws ResourceException;
    @Test
    public void deleteCard() throws Exception{
        String methodName = "DELETE CARD";
        //Mock
        Mockito.when(repository.findById(CARD_ID)).thenReturn(Optional.empty());
        try{
            service.deleteCard(CARD_ID);
        }catch (NotFoundException e){
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"EXCEPTION PRODUCT NOT FOUND PASSED"+Util.ANSI_RESET);
        }
        Mockito.when(repository.findById(CARD_ID)).thenReturn(Optional.of(CARD));
        Mockito.when(repository.save(Mockito.any(Card.class))).thenReturn(CARD);
        String response;
        //Get response
        try {
            response = service.deleteCard(CARD_ID);
        }catch(ResourceException e){
            System.out.println(Util.ANSI_RED+"SERVICE ERROR"+Util.ANSI_RESET);
            return; //Rompemos la funcion
            //No podemos validar si no tenemos un response.
        }
        //Assertions:
        try {
            Util.assertNotNull("RESPONSE NOT NULL",response);
            Util.assertEquals("GET CARD NUMBER",response, CARD_NUMBER.toString());
            Util.assertEquals("GET STATE",CARD.getState(), STATE_DELETED);
            System.out.println(methodName + " - "+Util.ANSI_GREEN+"TESTS PASSED"+Util.ANSI_RESET);
        }catch(AssertionError e){
            System.out.println(methodName +" - "+Util.ANSI_RED+"TESTS FAILED"+Util.ANSI_RESET);
            throw e;//Throweamos e para que el compilador identifique el error en el test
        }
    }
    private void validateCard(CardDto entity, CardDto mapped) throws AssertionError{
        Util.assertNotNull("CARD NOT NULL",entity);
        Util.assertEquals("CARD ID",entity.getId(),mapped.getId());
        Util.assertEquals("CARD TYPE",entity.getType(),mapped.getType());
        Util.assertEquals("CARD OWNER",entity.getOwner(),mapped.getOwner());
        Util.assertEquals("CARD NUMBER",entity.getCardNumber(),mapped.getCardNumber());
        Util.assertEquals("CARD EXPIRE DATE",entity.getExpireDate(),mapped.getExpireDate());
        Util.assertEquals("CARD MONEY",entity.getCardMoney(),mapped.getCardMoney());
    }
    private CreateCardDto convertToCreateResource(Card entity){
        return modelMapper.map(entity,CreateCardDto.class);
    }
    private UpdateCardDto convertToUpdateResource(Card entity){
        return modelMapper.map(entity,UpdateCardDto.class);
    }
    private CardDto convertToResource(Card entity){
        return modelMapper.map(entity,CardDto.class);
    }
}
