package se.yrgo.integrations;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LendingStepDefinitions {
    private WebDriver driver;

    public LendingStepDefinitions() {
        this.driver = GeneralStepDefinitions.getDriver();
    }

    @Given("navigates to {string}.")
    public void navigates_to(String navigatetion) {
        
    }

    @Given("the book with id {string} is available.")
    public void the_book_with_id_is_available(String string) {
        
    }

    @Given("the user with id {string} exists.")
    public void the_user_with_id_exists(String string) {
        
    }

    @When("the administrator lends book {string} to user {string}.")
    public void the_administrator_lends_book_to_user(String string, String string2) {
        
    }

    @Then("the book {string} should be registered as loaned to user {string}.")
    public void the_book_should_be_registered_as_loaned_to_user(String string, String string2) {
        
    }

    @When("the user has at least one loan.")
    public void the_user_has_at_least_one_loan() {
        
    }

    @Then("the user see the text {string}.")
    public void the_user_see_the_text(String string) {
        
    }
}