package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Локаторы вкладок
    private By bunsTab = By.xpath("//span[text()='Булки']/..");
    private By saucesTab = By.xpath("//span[text()='Соусы']/..");
    private By fillingsTab = By.xpath("//span[text()='Начинки']/..");

    // Методы клика по вкладкам
    @Step("Перейти на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Перейти на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Перейти на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    // Методы проверки активности вкладки
    public boolean isBunsTabActive() {
        wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
        return driver.findElement(bunsTab).getAttribute("class").contains("tab_tab_type_current");
    }

    public boolean isSaucesTabActive() {
        wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
        return driver.findElement(saucesTab).getAttribute("class").contains("tab_tab_type_current");
    }

    public boolean isFillingsTabActive() {
        wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
        return driver.findElement(fillingsTab).getAttribute("class").contains("tab_tab_type_current");
    }
}
