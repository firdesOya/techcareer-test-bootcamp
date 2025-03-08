
import Base.BaseLibrary;
import Base.BaseTest;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.ProductListPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPageTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage = new LoginPage();
    ProductListPage productListPage=new ProductListPage();
    BaseLibrary baseLibrary = new BaseLibrary();


    @BeforeMethod
    public void setUp() {
        mainPage = new MainPage();

    }

    @Test
    @Step("Japonya Araması Yap ve İlk Ürüne Tıkla")
    public void searchTest() {
        loginPage.fillEmail(email)
                .fillPassword(password)
                .clickLoginButton();
        Allure.step("✅ Login işlemi yapıldı.");

        mainPage.fillSearch("japonya");
        Allure.step("✅ Arama yapıldı.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[class='list']")));


        List<WebElement> products = driver.findElements(By.cssSelector("div > div:nth-child(2) > a"));
        for (WebElement product : products) {
            Allure.step("🔍 Bulunan ürün: " + product.getText());
        }

        System.out.println("✅ Ürüne tıklanıyor...");
        productListPage.firstProductClick();
        Allure.step("✅ Ürüne tıklandı.");

        baseLibrary.switchTab(1);
        Allure.step("✅ Yeni sekmeye geçildi.");

        screenshot();
        Allure.step("✅ Test tamamlandı.");
    }
}