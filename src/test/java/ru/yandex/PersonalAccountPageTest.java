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
import ru.yandex.pages.PersonalAccountPage;
import ru.yandex.pages.RegisterPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonalAccountPageTest extends StaticData {

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

    @DisplayName("Проверка авторизации")
    @Test
    public void authorizationTest() {
        loginPage = new LoginPage(driver);
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

    @DisplayName("Проверка выхода из личного кабинета")
    @Test
    public void exitFromPersonalAccount() {
        loginPage = new LoginPage(driver);
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
        personalAccountPage.clickExitButton();
        String actualTitle = loginPage.textInAuthorizationTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Вход"));
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
