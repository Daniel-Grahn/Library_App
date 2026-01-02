package se.yrgo.integrations;

import io.cucumber.java.en.*;
import se.yrgo.integrations.utility.pages.SearchPage;
import se.yrgo.integrations.utility.pages.StartPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
