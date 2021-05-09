package com.example.IntegrationTests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class S08Steps {
    @Given("a user8")
    public void aUser8() {
    }

    @When("makes a payment with new card")
    public void makesAPaymentWithNewCard() {
    }

    @Then("the system shows the message to save the card")
    public void theSystemShowsTheMessageToSaveTheCard() {
    }

    @When("tries to make a payment with saved card")
    public void triesToMakeAPaymentWithSavedCard() {
    }

    @Then("the system completes the fields with the saved card's info")
    public void theSystemCompletesTheFieldsWithTheSavedCardSInfo() {
    }

    @When("tries to makes a payment with incomplete saved card's info")
    public void triesToMakesAPaymentWithIncompleteSavedCardSInfo() {
    }

    @Then("the system does not show the message to save the card")
    public void theSystemDoesNotShowTheMessageToSaveTheCard() {
    }
}
