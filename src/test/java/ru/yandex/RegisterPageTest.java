package ru.yandex;

import driver.DriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.data.StaticData;
import ru.yandex.pages.LoginPage;
import ru.yandex.pages.MainPage;
import ru.yandex.pages.RegisterPage;

import java.time.Duration;

public class RegisterPageTest extends StaticData {

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

        registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.typeName();
        registerPage.typeEmail();

    }

    @DisplayName("Проверка регистрации нового пользователя")
    @Test
    public void registrationTest() {
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
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.titlePerAccountButtonAfterAuth().contains("Оформить заказ"));
    }

    @DisplayName("Проверка регистрации с некорректным паролем")
    @Test
    public void registrationWithIncorrectPasswordTest() {
        registerPage.typeIncorrectPassword();
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.textError().contains("Некорректный пароль"));
    }

    @DisplayName("Проверка авторизации через страницу регистрации")
    @Test
    public void authorizationFromRegistrationPageTest() {
        registerPage.typePassword();
        registerPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                driver.getCurrentUrl().contains("login"));
        loginPage = new LoginPage(driver);
        loginPage.clickRegistrationButton(); //
        registerPage = new RegisterPage(driver);
        registerPage.clickLoginButton();
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                registerPage.getItemFromLocalStorage("accessToken") != null);
        accessToken = registerPage.getItemFromLocalStorage("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.titlePerAccountButtonAfterAuth().contains("Оформить заказ"));
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
