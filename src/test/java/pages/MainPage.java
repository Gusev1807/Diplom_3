package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы на главной странице
    private By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private By loginButtonOnMain = By.xpath("//button[text()='Войти в аккаунт']");
    private By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");

    // Методы действий
    @Step("Открыть главную страницу Stellar Burgers")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной")
    public void clickLoginButtonOnMain() {
        driver.findElement(loginButtonOnMain).click();
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isPlaceOrderButtonDisplayed() {
        return driver.findElement(placeOrderButton).isDisplayed();
    }
}





