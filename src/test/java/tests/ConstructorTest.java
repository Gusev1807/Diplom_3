package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/"); // главная страница
        constructorPage = new ConstructorPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка переключения вкладок ингредиентов")
    @Description("Проверяет, что пользователь может переключаться между вкладками Булки, Соусы и Начинки на главной странице конструктора.")
    public void testTabsSwitching() {
        // Булки активны по умолчанию
        assertTrue("Булки должны быть активны по умолчанию", constructorPage.isBunsTabActive());

        // Переключаемся на Соусы
        constructorPage.clickSaucesTab();
        assertTrue("Соусы должны быть активны", constructorPage.isSaucesTabActive());

        // Переключаемся на Начинки
        constructorPage.clickFillingsTab();
        assertTrue("Начинки должны быть активны", constructorPage.isFillingsTabActive());

        // Возвращаемся на Булки
        constructorPage.clickBunsTab();
        assertTrue("Булки должны быть активны после переключения обратно", constructorPage.isBunsTabActive());
    }
}












