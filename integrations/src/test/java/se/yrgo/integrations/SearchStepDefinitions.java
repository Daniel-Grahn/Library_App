package se.yrgo.integrations;

import io.cucumber.java.en.*;
import se.yrgo.integrations.utility.pages.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SearchStepDefinitions {
    private WebDriver driver;

    public SearchStepDefinitions() {
        this.driver = GeneralStepDefinitions.getDriver();
    }

    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        StartPage startPage = new StartPage(driver);
        startPage.clickToSertch();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/search"));
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        SearchPage searchPage = new SearchPage(driver);

        assertTrue(searchPage.isSearchFormVisible());
    }

    @When("the user submits an empty search.")
    public void the_user_submits_an_empty_search() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.submitSearch();
    }

    @Then("they see the message {string}.")
    public void they_see_the_message(String message) {
        SearchPage searchPage = new SearchPage(driver);
        String pageMessage = searchPage.getErrorMessage();
        assertEquals(message, pageMessage);
    }
    
    @When("the user searches for isbn {string}.")
    public void the_user_searches_for_isbn(String isbn) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.enterIsbn(isbn);
        searchPage.submitSearch();
    }

    @Then("they see {string} as the author.")
    public void they_see_as_the_author(String author) {
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.isResultsVisible(), "Results should be visible");
        assertEquals(author, searchPage.getFirstAuthor());
    }
}
