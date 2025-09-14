package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Локаторы
    private By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private By nameField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    private By emailField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    private By passwordField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[3]");
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    // Методы действий
    @Step("Перейти на страницу регистрации")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Заполнить поле Имя: {name}")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    @Step("Заполнить поле Email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Заполнить поле Пароль: {password}")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        By errorLocator = By.xpath("//p[contains(text(),'Некорректный пароль')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator)).getText();
    }
}






