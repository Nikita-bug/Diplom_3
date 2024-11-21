package ru.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.data.StaticData;
import ru.yandex.pages.LoginPage;
import ru.yandex.pages.MainPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPageTest extends StaticData {

    @Before
    public void setUp() throws Exception {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
    @DisplayName("Переход на главную страницу с помощью кнопки 'Конструктор'")
    @Test
    public void transitionFromLoginPageToHomePageByConstructor(){
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        mainPage.clickConstructorButton();
        String actualTitle = mainPage.textConstructorTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Соберите бургер"));
    }
    @DisplayName("Переход на главную страницу с помощью клика по логотипу")
    @Test
    public void transitionFromLoginPageToHomePageByLogo(){
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        mainPage.clickLogoButton();
        String actualTitle = mainPage.textConstructorTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Соберите бургер"));
    }











    @After
    public void after() {
        driver.quit();
    }











}
