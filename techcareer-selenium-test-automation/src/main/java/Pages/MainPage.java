package Pages;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MainPage extends BaseTest {

    @Step("Kullanıcı bilgisi alınır")
    public String getAccount() {
        return driver.findElements(By.cssSelector("a.logg.turuncu.tlgn")).get(0).getText();
    }

    @Step("Arama alanına veri girişi sağlanır")
    public MainPage fillSearch(String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#headara input")));

        searchBox.sendKeys(text, Keys.ENTER);
        return this;
    }
}
