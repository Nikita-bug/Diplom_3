package ru.yandex;

import driver.DriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.pages.LoginPage;
import ru.yandex.pages.MainPage;
import ru.yandex.pages.PersonalAccountPage;
import ru.yandex.pages.RegisterPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


public class MainPageTest {

    private static WebDriver driver;
    private static String accessToken;
    private static RegisterPage registerPage;
    private static LoginPage loginPage;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        driver = DriverCreator.createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }


    @DisplayName("Переход в секцию с начинками")
    @Test
    public void transitionToSectionIngredientsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickIngredientSection();
        WebElement element =
                driver.findElement(By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']"));

        boolean isElementInViewport =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });
        assertTrue(mainPage.selectedIngredientButton().contains("type_current"));
    }

    @DisplayName("Переход в секцию с соусами")
    @Test
    public void transitionToSectionSaucesTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSaucesSection();
        WebElement element =
                driver.findElement(By.xpath(".//img[@alt='Соус Spicy-X']"));

        boolean isElementInViewport =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });

        assertTrue(mainPage.selectedSousesButton().contains("type_current"));
    }

    @DisplayName("Переход в секцию с булочками")
    @Test
    public void transitionToSectionBunsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickIngredientSection();
        mainPage.clickBunsSection();
        WebElement element =
                driver.findElement(By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']"));

        boolean isElementInViewport =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(
                                driver -> {
                                    Rectangle rect = element.getRect();
                                    Dimension windowSize = driver.manage().window().getSize();

                                    // условие, которое проверяем внутри явного ожидания
                                    return rect.getX() >= 0
                                            && rect.getY() >= 0
                                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                                });
        assertTrue(mainPage.selectedBunButton().contains("type_current"));
    }

    @DisplayName("Переход на страницу авторизации из хэдера")
    @Test
    public void transitionToLoginPageFromHeaderTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButtonInHeader();
        String actualTitle = loginPage.textInAuthorizationTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Вход"));
    }

    @DisplayName("Переход на страницу авторизации через кнопку по центру страницы")
    @Test
    public void transitionToLoginPageFromButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        String actualTitle = loginPage.textInAuthorizationTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Вход"));
    }


    @DisplayName("Авторизация на сайте через кнопку в хэдере страницы")
    @Test
    public void authorizationFromLoginButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.typeName();
        registerPage.typeEmail();
        registerPage.typePassword();
        registerPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                driver.getCurrentUrl().contains("login"));
        loginPage = new LoginPage(driver);
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                registerPage.getItemFromLocalStorage("accessToken") != null);
        accessToken = registerPage.getItemFromLocalStorage("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        mainPage.clickPersonalAccountButtonInHeader();
        assertTrue(personalAccountPage.textInConfirmation().contains("Сохранить"));
    }

    @DisplayName("Авторизация на сайте через кнопку по центру страницы")
    @Test
    public void authorizationFromPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.typeName();
        registerPage.typeEmail();
        registerPage.typePassword();
        registerPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                driver.getCurrentUrl().contains("login"));
        loginPage = new LoginPage(driver);
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                registerPage.getItemFromLocalStorage("accessToken") != null);
        accessToken = registerPage.getItemFromLocalStorage("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
        mainPage.clickPerAccountButtonAfterAuth();
        assertTrue(mainPage.textOrderTitle().contains("идентификатор заказа"));
    }


    @After
    public void after() {
        if (accessToken != null) {
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.delete(accessToken);
        }
        driver.quit();
    }


}
