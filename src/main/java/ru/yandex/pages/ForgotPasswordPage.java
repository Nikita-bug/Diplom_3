package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.data.StaticData;

public class ForgotPasswordPage extends StaticData {

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By LOGIN_BUTTON = By.xpath(".//a[contains(text(),'Войти')]");

    @Step("Клик по кнопке авторизации")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }


}
