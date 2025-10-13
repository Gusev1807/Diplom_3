package tests;

import driver.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.ConstructorPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        // Делал через Sleep чтобы себя перепроверить. Переписал вот так в итоге.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        constructorPage = new ConstructorPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    @DisplayName("Проверка вкладки 'Булки'")
    @Description("Проверяет, что вкладка 'Булки' активна после переключения на другую вкладку и возврата")
    public void testBunsTab() {
        constructorPage.clickSaucesTab();
        constructorPage.clickBunsTab();

        assertTrue("Булки должны быть активны", constructorPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Проверка вкладки 'Соусы'")
    @Description("Проверяет, что вкладка 'Соусы' активна после клика")
    public void testSaucesTab() {
        constructorPage.clickSaucesTab();

        assertTrue("Соусы должны быть активны", constructorPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Проверка вкладки 'Начинки'")
    @Description("Проверяет, что вкладка 'Начинки' активна после клика")
    public void testFillingsTab() {
        constructorPage.clickFillingsTab();

        assertTrue("Начинки должны быть активны", constructorPage.isFillingsTabActive());
    }
}












