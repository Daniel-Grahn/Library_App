package se.yrgo.integrations.utility.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SearchPage {
    private WebDriver driver;

    private By searchFormLocator = By.cssSelector("form.bg-base-300");
    private By isbnInput = By.cssSelector("input[placeholder='ISBN']");
    private By searchButton = By.cssSelector("input[type='submit']");
    private By errorMessage = By.cssSelector("section.errors div");
    private By resultsSection = By.cssSelector("section.found-items");
    private By firstAuthor = By.cssSelector("section.found-items tbody tr td:nth-child(2)");


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void moveToSearchPage() {
        driver.get("http://frontend/search");
    }

    public WebElement getSearchForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchFormLocator));
    }

    public boolean isSearchFormVisible() {
        return getSearchForm().isDisplayed();
    }

    public void enterIsbn(String isbn) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(isbnInput));

        input.clear();
        input.sendKeys(isbn);
    }

    public void submitSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(searchButton));

        button.click();
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return msg.getText();
        } catch (TimeoutException e) {
            return null; 
        }
    }

    public boolean isResultsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsSection));
            return results.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getFirstAuthor() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement author = wait.until(ExpectedConditions.visibilityOfElementLocated(firstAuthor));
            return author.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

}
