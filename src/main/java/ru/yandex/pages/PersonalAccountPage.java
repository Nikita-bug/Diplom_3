package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.data.StaticData;

public class PersonalAccountPage extends StaticData {

    private WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By PERSONAL_ACCOUNT_CONFIRMATION = By.xpath(".//button[contains(text(),'Сохранить')]");
    public static final By EXIT_BUTTON = By.xpath(".//button[contains(text(),'Выход')]");


    @Step("Возвращает текст, подтверждающий, что мы находимся на странице личного кабинета")
    public String textInConfirmation() {
        return driver.findElement(PERSONAL_ACCOUNT_CONFIRMATION).getText();
    }

    @Step("Клик по кнопке выхода")
    public void clickExitButton() {
        driver.findElement(EXIT_BUTTON).click();
    }


}
