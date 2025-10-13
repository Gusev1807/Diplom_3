package tests;

import steps.UserSteps;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import pojo.User;
import io.restassured.response.Response;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private MainPage mainPage;
    private RegisterPage registerPage;
    private LoginPage loginPage;

    private User testUser;
    private String accessToken;

    @Before
    public void setUp() {
        Faker faker = new Faker();
        testUser = new User(
                faker.internet().emailAddress(),
                "password123",
                faker.name().fullName()
        );

        // Создание пользователя через API
        Response response = UserSteps.createUser(testUser);
        accessToken = response.path("accessToken");

        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
        if (driver != null) driver.quit();
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверка входа зарегистрированного пользователя через кнопку 'Войти в аккаунт'")
    public void loginViaMainButton() {
        mainPage.openMainPage();
        mainPage.clickLoginButtonOnMain();

        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Пользователь не вошёл в систему!", mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа зарегистрированного пользователя через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccount() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();

        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Пользователь не вошёл в систему!", mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти' в форме регистрации")
    @Description("Проверка входа через кнопку 'Войти' на странице регистрации")
    public void loginViaRegisterForm() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        loginPage.clickLoginFromRegisterForm();

        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Пользователь не вошёл в систему!", mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Восстановить пароль'")
    @Description("Проверка перехода на восстановление пароля")
    public void loginViaForgotPassword() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        loginPage.clickForgotPassword();

        assertTrue("Страница восстановления пароля не открыта!", loginPage.isPasswordRecoveryEmailFieldDisplayed());
    }
}





