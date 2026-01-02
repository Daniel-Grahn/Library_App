package se.yrgo.integrations.utility.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class SearchPage {
    private WebDriver driver;

    private By searchFormLocator = By.cssSelector("form.bg-base-300");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSearchForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchFormLocator));
    }

    public boolean isSearchFormVisible() {
        return getSearchForm().isDisplayed();
    }
}
