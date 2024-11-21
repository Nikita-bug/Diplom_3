package ru.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.data.StaticData;
import ru.yandex.pages.LoginPage;
import ru.yandex.pages.MainPage;
import ru.yandex.pages.PersonalAccountPage;
import ru.yandex.pages.RegisterPage;

import java.time.Duration;

public class RegisterPageTest extends StaticData {


    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();


    }

    @DisplayName("Регистрация")
    @Test
    public void registrationTest() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.openRegisterPage();
        registerPage.typeName();
        registerPage.typeEmail();
        registerPage.typePassword();
        registerPage.clickRegisterButton();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.textLoginTitle().contains("Вход"));
    }

    @DisplayName("Регистрация с некорректным паролем")
    @Test
    public void registrationWithIncorrectPasswordTest() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.openRegisterPage();
        registerPage.typeName();
        registerPage.typeEmail();
        registerPage.typeIncorrectPassword();
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.textError().contains("Некорректный пароль"));
    }

    @DisplayName("Авторизация через страницу регистрации")
    @Test
    public void authorizationFromRegistrationPageTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage();
        loginPage.clickRegistrationButton();
        RegisterPage registerPage = new RegisterPage();
        registerPage.clickLoginButton();
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        mainPage.clickPersonalAccountButtonInHeader();
        Assert.assertTrue("Отсутствует кнопка Сохранить", personalAccountPage.textInConfirmation().contains("Сохранить"));
    }


    @After
    public void after() {
        driver.quit();
    }


}
