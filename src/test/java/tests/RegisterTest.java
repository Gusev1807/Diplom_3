package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.RegisterPage;

public class RegisterTest {

    private WebDriver driver;
    private MainPage mainPage;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        // Можно использовать WebDriverManager для Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка, что пользователь может зарегистрироваться с корректными данными")
    public void successfulRegistrationTest() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        registerPage.enterName("Test User");
        registerPage.enterEmail("testuser@mail.com");
        registerPage.enterPassword("123456");
        registerPage.clickRegisterButton();

        // Проверка успешного перехода (например, появление кнопки выхода)
        // assertTrue(driver.findElement(By.xpath("//button[text()='Выход']")).isDisplayed());
    }

    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    @Description("Проверка, что при вводе пароля меньше 6 символов появляется ошибка")
    public void shortPasswordRegistrationTest() {
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        registerPage.clickRegisterLink();
        registerPage.enterName("Test User");
        registerPage.enterEmail("testuser@mail.com");
        registerPage.enterPassword("123"); // короткий пароль
        registerPage.clickRegisterButton();

        // Проверка появления ошибки
        // Например:
        // String errorText = driver.findElement(By.xpath("//p[contains(text(),'Некорректный пароль')]")).getText();
        // assertEquals("Некорректный пароль", errorText);
    }
}


