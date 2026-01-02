package se.yrgo.integrations;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

import java.net.*;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import se.yrgo.integrations.utility.pages.SearchPage;
import se.yrgo.integrations.utility.pages.StartPage;

public class GeneralStepDefinitions {
    private static WebDriver driver;

    @Before
    public void setupWebDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://localhost:4444"),
                    options, false);
        } 
        catch (MalformedURLException e) {
            fail(e);
        }
    }

    @After
    public void shutdownWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Given("the user is on the start page.")
    public void the_user_is_on_the_start_page() {
        StartPage startPage = new StartPage(driver);
        startPage.moveToStartPage();
        
        if (!"The Library".equals(driver.getTitle())) {
            throw new IllegalStateException("Not on the start page");
        }
    }
    
    @Given("the user is on the search page.")
    public void the_user_is_on_the_search_page() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.moveToSearchPage();
        
        if (!"The Library".equals(driver.getTitle())) {
            throw new IllegalStateException("Not on the Search page");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/search"));
    }
}