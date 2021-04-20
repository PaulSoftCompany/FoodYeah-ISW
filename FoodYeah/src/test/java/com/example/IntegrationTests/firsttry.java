package com.example.IntegrationTests;

import com.example.entity.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Vector;

public class firsttry {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerCategoryService customerCategoryService;
    @Autowired
    private CardService cardService;
    @Autowired
    private RoleService roleService;

    private Order order;
    private Card card;
    private OrderDetail detail;
    private Product product;
    private Customer customer;
    private CustomerCategory customerCategory;
    private ProductCategory productCategory;
    private Role role;

    private boolean valid;

    void GenerarDatos(){

        ///////////////////////////////////////////////
        productCategory = new ProductCategory();
        productCategory.setProductCategoryName("Category");
        productCategory.setProductCategoryDescription("test");
        categoryService.createProduct_Category(productCategory);

        /////////////////////////////////////////////////
        product = new Product();
        product.setProductName("Product");
        product.setCategory(productCategory);
        product.setStock(20);
        String[] ingredients = {"Ingredient1", "Ingredient2"};
        product.setIngredients(ingredients);
        product.setProductPrice(30);
        product.setImageUrl("test");
        productService.createProduct(product);

        //////////////////////////////////////////////////
        detail = new OrderDetail();
        byte quantity = 2;
        detail.setQuantity(quantity);
        detail.setProduct(product);

        ///////////////////////////////////////////////
        customerCategory = new CustomerCategory();
        customerCategory.setCustomerCategoryName("Test");
        customerCategory.setCustomerCategoryDescription("Test");
        customerCategoryService.createCustomerCategory(customerCategory);

        ////////////////////////////////////////////
        role = new Role();
        role.setRoleName("Admin");
        role.setRoleDescription("Admin");
        roleService.createRole(role);
        role.setRoleName("User");
        role.setRoleDescription("User");
        roleService.createRole(role);

        /////////////////////////////////////////
        customer = new Customer();
        byte age = 20;
        customer.setCustomerAge(age);
        customer.setCustomerName("TestName");
        customer.setCustomerCategory(customerCategory);
        customer.setUsername("asername@upc.edu.pe");
        customer.setPassword("ptestr41241d");
        customerService.createCustomer(customer);

        //////////////////////////////////////////////////
        card = new Card();
        card.setCardCvi(123);
        card.setCardNumber("test");
        card.setCardExpireDate("test");
        card.setCardMoney(150);
        card.setCardType(true);
        cardService.createCard(card);


        //////////////////////////////////////////////
        List<OrderDetail> _orderdetail;
        _orderdetail = new Vector<OrderDetail>();
        _orderdetail.add(detail);
        order = new Order();
        order.setCostumer(customer);
        order.setOrderDetails(_orderdetail);


    }

    @Given("a user who wants to make a transaction")
    public void aUserWhoWantsToMakeATransaction() {
        GenerarDatos();
        Order recipt = orderService.createOrder(order);
    }

    @And("does not have his card affiliated to the application")
    public void doesNotHaveHisCardAffiliatedToTheApplication() {
        if(cardService.getAllByCustomerId(customer.getId()).size() == 0){
            valid = false;
        }
    }

    @When("he does not enter all the data or the data is not valid,")
    public void heDoesNotEnterAllTheDataOrTheDataIsNotValid() {

    }

    @Then("the user will not be notified of the option to affiliate his card.")
    public void theUserWillNotBeNotifiedOfTheOptionToAffiliateHisCard() {
        //TODO
    }

/*    @Given("a user who does not have her card affiliated with the application")
    public void aUserWhoDoesNotHaveHerCardAffiliatedWithTheApplication() {
    }

    @When("the user fills in her data and makes a purchase,")
    public void theUserFillsInHerDataAndMakesAPurchase() {
    }

    @Then("she must notify the user if she wants the application to remember her data. In case the user says yes, the application must save them in the database, otherwise no.")
    public void sheMustNotifyTheUserIfSheWantsTheApplicationToRememberHerDataInCaseTheUserSaysYesTheApplicationMustSaveThemInTheDatabaseOtherwiseNo() {
    }

    @Given("a user who wants to carry out a transaction")
    public void aUserWhoWantsToCarryOutATransaction() {
    }

    @When("the application asks her to enter her data,")
    public void theApplicationAsksHerToEnterHerData() {
    }

    @Then("the application must show the user a box that allows the application to save the data she has entered")
    public void theApplicationMustShowTheUserABoxThatAllowsTheApplicationToSaveTheDataSheHasEntered() {
    }*/
}
