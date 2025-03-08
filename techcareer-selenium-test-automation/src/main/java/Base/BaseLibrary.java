package Base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseLibrary extends Data  {
    public static WebDriver driver;
    public void sleep (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchTab(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        int attempts = 0;
        while (driver.getWindowHandles().size() <= index && attempts < 5) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attempts++;
        }

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println("🔍 Mevcut sekmeler: " + tabs);

        if (tabs.size() > index) {
            driver.switchTo().window(tabs.get(index));
            System.out.println("✅ Yeni sekmeye geçildi: " + driver.getTitle());


            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'"));
            System.out.println("✅ Yeni sekme tamamen yüklendi.");


            String expectedUrl = "https://www.gruppal.com/kore-japonya-turlari-seul-tokyo-kyoto-osaka";
            wait.until(ExpectedConditions.urlContains(expectedUrl));
            System.out.println("✅ Beklenen URL açıldı: " + driver.getCurrentUrl());


            if (!driver.getCurrentUrl().contains(expectedUrl)) {
                throw new IllegalStateException("❌ Yeni sekmede yanlış sayfa açıldı!");
            }
        } else {
            throw new IllegalStateException("❌ Yeni sekme açılmadı veya index hatalı!");
        }
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
