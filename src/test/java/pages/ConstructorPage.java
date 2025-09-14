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

    // Константа для активного класса вкладки
    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current";

    // Локаторы вкладок
    private By bunsTab = By.xpath("//span[text()='Булки']/..");
    private By saucesTab = By.xpath("//span[text()='Соусы']/..");
    private By fillingsTab = By.xpath("//span[text()='Начинки']/..");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

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

    // Метод проверки активности вкладки
    private boolean isTabActive(By tabLocator) {
        wait.until(ExpectedConditions.attributeContains(tabLocator, "class", ACTIVE_TAB_CLASS));
        return driver.findElement(tabLocator).getAttribute("class").contains(ACTIVE_TAB_CLASS);
    }

    // Методы проверки активности каждой вкладки
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }
}
