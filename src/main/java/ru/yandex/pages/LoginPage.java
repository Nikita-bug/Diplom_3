package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.data.StaticData;

public class LoginPage extends StaticData {

    //Заголовок формы входа
    public static final By AUTHORIZATION_WINDOW_TITLE = By.xpath(".//div[@class = 'Auth_login__3hAey']");
    public static final By LOGIN_TITLE = By.xpath(".//h2[contains(text(),'Вход')]");
    public static final By AUTHORIZATION_EMAIL_FIELD = By.xpath(".//fieldset[1]//input");
    public static final By AUTHORIZATION_PASSWORD_FIELD = By.xpath(".//fieldset[2]//input");
    public static final By AUTHORIZATION_BUTTON = By.xpath(".//button[contains(text(),'Войти')]");
    public static final By REGISTRATION_BUTTON = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");
    public static final By FORGOT_PASSWORD_BUTTON = By.xpath(".//a[contains(text(),'Восстановить пароль')]");


    @Step("Переход на страницу авторизации")
    public void openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
    }


    @Step("Получение текста заголовка формы авторизации")
    public String textInAuthorizationTitle() {
        return driver.findElement(AUTHORIZATION_WINDOW_TITLE).getText();
    }

    @Step("Заполнение поля почты")
    public void typeEmail() {
        driver.findElement(AUTHORIZATION_EMAIL_FIELD).sendKeys(EMAIL);
    }

    @Step("Заполнение поля пароля")
    public void typePassword() {
        driver.findElement(AUTHORIZATION_PASSWORD_FIELD).sendKeys(PASSWORD);
    }

    @Step("Клик по кнопке авторизации")
    public void clickAuthorizationButton() {
        driver.findElement(AUTHORIZATION_BUTTON).click();
    }

    @Step("Получение текста кнопки входа")
    public String textLoginTitle() {
        return driver.findElement(LOGIN_TITLE).getText();
    }

    @Step("Клик по кнопке перехода на страницу регистрации")
    public void clickRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Клик по кнопке перехода на страницу восстановления пароля")
    public void clickForgotPasswordButton() {
        driver.findElement(FORGOT_PASSWORD_BUTTON).click();
    }


}
