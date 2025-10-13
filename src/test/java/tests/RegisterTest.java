package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;
import pages.MainPage;
import pages.RegisterPage;
import pojo.User;
import steps.UserSteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {

    private WebDriver driver;
    private MainPage mainPage;
    private RegisterPage registerPage;

    private String email;
    private String fullName;
    private String password;
    private String accessToken;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);

        // Генерация уникальных данных через Faker
        Faker faker = new Faker();
        fullName = faker.name().fullName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 12);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();

        // Удаляем пользователя через API, если токен есть
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken)
                    .then()
                    .statusCode(202);
        }
    }

    private void registerNewUser() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        registerPage.enterName(fullName);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
    }

    @Test
    @DisplayName("Регистрация через UI, логин через API и удаление")
    @Description("Создание пользователя через UI, логин через API для проверки, что пользователь вошёл, и удаление пользователя через API")
    public void registerUiLoginApiDeleteTest() {
        // Регистрация через UI
        registerNewUser();

        // Логин через API
        User user = new User(email, password, fullName);
        Response loginResponse = UserSteps.loginUser(user);


        assertEquals("Логин через API не успешен!", 200, loginResponse.getStatusCode());
        accessToken = loginResponse.path("accessToken");
        assertTrue("Access token пустой!", accessToken != null && !accessToken.isEmpty());
    }

    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    @Description("Проверка, что при вводе пароля меньше 6 символов появляется ошибка")
    public void shortPasswordRegistrationTest() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        registerPage.enterName(fullName);
        registerPage.enterEmail("shortpass@test.com");
        registerPage.enterPassword("123"); // короткий пароль
        registerPage.clickRegisterButton();


        String errorText = registerPage.getPasswordErrorText();
        assertEquals("Некорректный пароль", errorText);
    }
}



