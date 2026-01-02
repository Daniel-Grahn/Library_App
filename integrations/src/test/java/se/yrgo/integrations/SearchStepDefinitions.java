package se.yrgo.integrations;

import io.cucumber.java.en.*;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement findBookButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a.btn.btn-primary[href='/search']")
                )
        );

        // Klicka på knappen
        findBookButton.click();
        wait.until(ExpectedConditions.urlContains("/search"));
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchForm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("form.bg-base-300")));

        assertTrue(searchForm.isDisplayed());
    }
}
