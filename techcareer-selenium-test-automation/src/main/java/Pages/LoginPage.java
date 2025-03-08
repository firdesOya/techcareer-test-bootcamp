package Pages;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BaseTest {

    @Step("E posta alanına veri girişi sağlanır")
    public LoginPage fillEmail (String email) {
        driver.findElement(By.id("nick")).sendKeys(email);
        return this;
    }

    @Step("Parola alanına veri girişi sağlanır")
    public LoginPage fillPassword (String password) {
        driver.findElement(By.name("sifre")).sendKeys(password);
        return this;
    }

    @Step("Giriş yap butonuna tıklanır")
    public LoginPage clickLoginButton () {
        driver.findElement(By.cssSelector("[class='kbtn fbcl tdb']")).click();
        return this;

    }
    @Step("Hata mesajı ekrandan çekilir")
    public String getErrorMessage () {
        return driver.findElement(By.cssSelector("div.hata ul li")).getText();
    }

}
