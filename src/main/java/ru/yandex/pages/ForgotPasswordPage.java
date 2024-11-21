package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.data.StaticData;

public class ForgotPasswordPage extends StaticData {


    public static final By LOGIN_BUTTON = By.xpath(".//a[contains(text(),'Войти')]");

    @Step("Клик по кнопке авторизации")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }


}
