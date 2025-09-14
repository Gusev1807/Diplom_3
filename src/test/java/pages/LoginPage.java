package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Локаторы для формы входа
    private By emailField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    private By passwordField = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By registerFormLoginButton = By.xpath("//a[text()='Войти']");
    private By forgotPasswordButton = By.xpath("//a[text()='Восстановить пароль']");

    // Методы действий
    @Step("Заполнить поле Email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Заполнить поле Пароль: {password}")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать кнопку 'Войти' в форме регистрации")
    public void clickLoginFromRegisterForm() {
        wait.until(ExpectedConditions.elementToBeClickable(registerFormLoginButton)).click();
    }

    @Step("Нажать кнопку 'Восстановить пароль'")
    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    @Step("Проверить, что отображено поле Email для восстановления пароля")
    public boolean isPasswordRecoveryEmailFieldDisplayed() {
        By emailFieldLocator = By.xpath("//input[@name='name']"); // или нужный xpath для поля email
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator)).isDisplayed();
    }

}





