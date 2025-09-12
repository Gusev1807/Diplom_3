package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private RegisterPage registerPage;
    private LoginPage loginPage;

    // Тестовые данные
    private String email;
    private final String password = "password123";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);

        // Генерация уникального email для регистрации
        email = "test" + System.currentTimeMillis() + "@yandex.ru";
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void registerNewUser() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        registerPage.enterName("Test User");
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        // После успешной регистрации возвращаемся на главную
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Регистрация нового пользователя и вход через кнопку 'Войти в аккаунт' на главной странице")
    public void loginViaMainButton() {
        registerNewUser();

        mainPage.clickLoginButtonOnMain();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Пользователь не вошёл в систему!", mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' на главной")
    @Description("Регистрация нового пользователя и вход через кнопку 'Личный кабинет' на главной странице")
    public void loginViaPersonalAccount() {
        registerNewUser();

        mainPage.clickPersonalAccount();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Пользователь не вошёл в систему!", mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме регистрации")
    @Description("Регистрация нового пользователя и вход через кнопку 'Войти' в форме регистрации")
    public void loginViaRegisterForm() {
        registerNewUser();

        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        loginPage.clickLoginFromRegisterForm();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertTrue("Пользователь не вошёл в систему!", mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Восстановить пароль'")
    @Description("Регистрация нового пользователя и переход к восстановлению пароля")
    public void loginViaForgotPassword() {
        registerNewUser();

        mainPage.clickPersonalAccount();
        loginPage.clickForgotPassword();

        // Здесь можно добавить assert для проверки перехода на страницу восстановления пароля
        // Например: assertTrue(driver.findElement(By.xpath("//h2[text()='Восстановление пароля']")).isDisplayed());
    }
}




