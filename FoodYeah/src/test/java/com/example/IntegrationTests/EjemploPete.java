package com.example.IntegrationTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EjemploPete {
    private int stock;
    private int initialStock;

    @Given("I have {int} beer cans")
    public void iHaveOpeningBalanceBeerCans(int initialStock) {
            this.initialStock = initialStock;
    }

    @When("I have drunk {int} beer cans")
    public void iHaveDrunkProcessedBeerCans(int processed) {
            this.stock = this.initialStock - processed;
    }

    @When("I go to my fridge")
    public void iGoToMyFridge() {
    }

    @Then("I should {int} beer cans")
    public void iShouldInStockBeerCans(int stock) {
        assertEquals(this.stock, stock);
    }
}
