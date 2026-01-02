package se.yrgo.integrations.utility.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class StartPage {
    private WebDriver driver;

    private By findABookLink = By.cssSelector("a.btn.btn-primary[href='/search']");
    
    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void moveToStartPage() {
        driver.get("http://frontend");
    }

    public void clickToSertch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement findBookButton = wait.until(
                ExpectedConditions.elementToBeClickable(findABookLink)
        );

        findBookButton.click();
    }
}