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

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class MainPageTest extends StaticData {


    @Before
    public void setUp() throws Exception {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }


    @DisplayName("Переход в секцию с начинками")
    @Test
    public void transitionToSectionIngredientsTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickIngredientSection();
        String ingredientsSection = mainPage.textFromIngredientSection();
        assertThat("Отсутствует необходимый заголовок", ingredientsSection, containsString("Начинки"));
    }

    @DisplayName("Переход в секцию с соусами")
    @Test
    public void transitionToSectionSaucesTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickSaucesSection();
        String ingredientsSection = mainPage.textFromSaucesSection();
        assertThat("Отсутствует необходимый заголовок", ingredientsSection, containsString("Соусы"));
    }

    @DisplayName("Переход в секцию с булочками")
    @Test
    public void transitionToSectionBunsTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickIngredientSection();
        mainPage.clickBunsSection();
        String ingredientsSection = mainPage.textFromBunsSection();
        assertThat("Отсутствует необходимый заголовок", ingredientsSection, containsString("Булки"));
    }

    @DisplayName("Переход на страницу авторизации из хэдера")
    @Test
    public void transitionToLoginPageFromHeaderTest() {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButtonInHeader();
        String actualTitle = loginPage.textInAuthorizationTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Вход"));
    }

    @DisplayName("Переход на страницу авторизации через кнопку по центру страницы")
    @Test
    public void transitionToLoginPageFromButtonTest() {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        String actualTitle = loginPage.textInAuthorizationTitle();
        assertThat("Отсутствует необходимый заголовок", actualTitle, containsString("Вход"));
    }


    @DisplayName("Авторизация на сайте через кнопку в хэдере страницы")
    @Test
    public void authorizationFromLoginButtonTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage();
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        mainPage.clickPersonalAccountButtonInHeader();
        Assert.assertTrue(personalAccountPage.textInConfirmation().contains("Сохранить"));
    }

    @DisplayName("Авторизация на сайте через кнопку по центру страницы")
    @Test
    public void authorizationFromPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage();
        loginPage.typeEmail();
        loginPage.typePassword();
        loginPage.clickAuthorizationButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage();
        mainPage.clickPersonalAccountButtonInHeader();
        Assert.assertTrue(personalAccountPage.textInConfirmation().contains("Сохранить"));
    }


    @After
    public void after() {
        driver.quit();
    }


}
