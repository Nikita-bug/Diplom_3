package ru.yandex;

import driver.DriverCreator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.data.StaticData;
import ru.yandex.pages.*;

import java.time.Duration;

public class ForgotPasswordPageTest extends StaticData {


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
        registerPage.typePassword();
        registerPage.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                driver.getCurrentUrl().contains("login"));
    }

    @DisplayName("Авторизация через страницу с восстановлением пароля")
    @Test
    public void authorizationFromForgotPasswordPageTest() {
        loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginButton();
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                registerPage.getItemFromLocalStorage("accessToken") != null);
        accessToken = registerPage.getItemFromLocalStorage("accessToken");
        accessToken = accessToken.replace("Bearer ", "");
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButtonInHeader();
        Assert.assertTrue("Отсутствует кнопка Сохранить", personalAccountPage.textInConfirmation().contains("Сохранить"));
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
