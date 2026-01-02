package se.yrgo.integrations;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

import java.net.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;

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
}