import Base.BaseTest;
import Pages.LoginPage;
import Pages.MainPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})

public class LoginTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();



    @Test(description = "Başarılı Kullanıcı Girişi")
    public void loginSuccessful() {
        successfulLoginSteps();
    }

    @Step("Başarılı Kullanıcı Giriş Testi")
    public void successfulLoginSteps() {
        Allure.step("📌 Kullanıcı giriş ekranına yönlendirildi.");

        loginPage.fillEmail(email)
                .fillPassword(password)
                .clickLoginButton();
        Allure.step("✅ Kullanıcı giriş bilgileri dolduruldu ve giriş yapıldı.");

        sleep(3000);

        String accountText = mainPage.getAccount();
        Assert.assertEquals(accountText, "Hesabım", "❌ Kullanıcı giriş yapamadı!");
        Allure.step("✅ Kullanıcı başarılı şekilde giriş yaptı.");

        screenshot();
    }

    @Test(description = "Başarısız Kullanıcı Girişi")
    public void loginUnsuccessful() {
        unsuccessfulLoginSteps();
    }

    @Step("Başarısız Kullanıcı Giriş Testi")
    public void unsuccessfulLoginSteps() {
        Allure.step("📌 Kullanıcı giriş ekranına yönlendirildi.");

        loginPage.fillEmail(email)
                .fillPassword(password + "44") // Hatalı şifre giriyoruz
                .clickLoginButton();
        Allure.step("🚫 Hatalı giriş bilgileri dolduruldu ve giriş denendi.");

        sleep(3000);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Geçersiz kullanıcı adı veya şifre", "❌ Hata mesajı beklenen gibi değil!");
        Allure.step("✅ Beklenen hata mesajı görüntülendi: " + errorMessage);

        screenshot();
    }


}
