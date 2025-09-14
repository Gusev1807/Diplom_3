package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import driver.DriverFactory;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        constructorPage = new ConstructorPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    // Написал чтоб визуально были видны переключения
    private void waitForDemo() {
        try {
            Thread.sleep(1000); // ждем 1 секунду
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Проверка вкладки 'Булки'")
    @Description("Проверяет, что вкладка 'Булки' активна после переключения на другую вкладку и возврата")
    public void testBunsTab() {
        constructorPage.clickSaucesTab();
        waitForDemo();

        constructorPage.clickBunsTab();
        waitForDemo();

        assertTrue("Булки должны быть активны", constructorPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Проверка вкладки 'Соусы'")
    @Description("Проверяет, что вкладка 'Соусы' активна после клика")
    public void testSaucesTab() {
        constructorPage.clickSaucesTab();
        waitForDemo();

        assertTrue("Соусы должны быть активны", constructorPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Проверка вкладки 'Начинки'")
    @Description("Проверяет, что вкладка 'Начинки' активна после клика")
    public void testFillingsTab() {
        constructorPage.clickFillingsTab();
        waitForDemo();

        assertTrue("Начинки должны быть активны", constructorPage.isFillingsTabActive());
    }
}












