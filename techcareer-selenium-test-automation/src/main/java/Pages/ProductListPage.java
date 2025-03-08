package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Base.BaseLibrary.driver;

public class ProductListPage {


@Step("Ürün listesindeki ilk ürüne tıklanır")
public void firstProductClick() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    By firstProductLocator = By.cssSelector("div > div:nth-child(2) > a");

    WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(firstProductLocator));

    String productUrl = firstProduct.getAttribute("href");
    System.out.println("🔗 Ürün linki: " + productUrl);

    ((JavascriptExecutor) driver).executeScript("window.open(arguments[0]);", productUrl);
    System.out.println("✅ Ürün yeni sekmede açıldı!");
}}