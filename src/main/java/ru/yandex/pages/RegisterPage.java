package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.data.StaticData;

import static io.restassured.RestAssured.given;

public class RegisterPage extends StaticData {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    protected static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    public static final By LOGIN_BUTTON = By.xpath(".//a[contains(text(),'Войти')]");
    public static final By ERROR_MESSAGE = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    public static final By NAME_FIELD = By.xpath(".//fieldset[1]//input");
    public static final By EMAIL_FIELD = By.xpath(".//fieldset[2]//input");
    public static final By PASSWORD_FIELD = By.xpath(".//fieldset[3]//input");
    public static final By REGISTER_BUTTON = By.xpath(".//div/form/button");


    @Step("Переход на страницу регистрации")
    public void openRegisterPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Заполнение поля имени")
    public void typeName() {
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step("Заполнение поля почты")
    public void typeEmail() {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Заполнение поля пароля")
    public void typePassword() {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Заполнение поля пароля некорректными данными")
    public void typeIncorrectPassword() {
        driver.findElement(PASSWORD_FIELD).sendKeys(incorrectPassword);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Клик по кнопке перехода на страницу авторизации")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Получение текста ошибки при попытке авторизации с некорректным паролем")
    public String textError() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Удаление пользователя")
    public void delete(String accessToken) {
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/auth/user");

    }

    public String getItemFromLocalStorage(String accessToken) {
        return (String)
                ((JavascriptExecutor) driver)
                        .executeScript(String.format("return window.localStorage.getItem('%s');", accessToken));
    }
}
