package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("BROWSER", "chrome").toLowerCase();
        ChromeOptions options = new ChromeOptions();

        switch (browser) {
            case "chrome":
            case "yandex":
                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browser);
        }
    }
}

