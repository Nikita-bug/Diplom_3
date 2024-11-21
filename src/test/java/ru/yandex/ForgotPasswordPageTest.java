package ru.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.data.StaticData;
import ru.yandex.pages.ForgotPasswordPage;
import ru.yandex.pages.LoginPage;
import ru.yandex.pages.MainPage;
import ru.yandex.pages.PersonalAccountPage;

import java.time.Duration;

public class ForgotPasswordPageTest extends StaticData {


    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();


    }

    @DisplayName("Авторизация через страницу с восстановлением пароля")
    @Test
    public void authorizationFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage();
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.clickLoginButton();
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
