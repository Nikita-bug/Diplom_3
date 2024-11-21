package ru.yandex.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.yandex.data.StaticData;

public class MainPage extends StaticData {

    //Кнопка Личного кабинета в хедере
    public static final By PERSONAL_ACCOUNT = By.xpath(".//div/header/nav/a");
    public static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//button[@class = 'button_button__33qZ0" +
            " button_button_type_primary__1O7Bx button_button_size_large__G21Vg'][text()='Войти в аккаунт']");
    public static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va" +
            " ml-2'][text()='Конструктор']");
    public static final By CONSTRUCTOR_TITLE = By.xpath(".//h1[@class = 'text text_type_main-large" +
            " mb-5 mt-10'][text()='Соберите бургер']");
    public static final By SITE_LOGO = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    public static final By SECTION_INGREDIENTS = By.xpath(".//span[contains(text(),'Начинки')]");
    public static final By SECTION_SAUCES = By.xpath(".//span[contains(text(),'Соусы')]");
    public static final By SECTION_BUNS = By.xpath(".//span[contains(text(),'Булки')]");
    public static final By SECTION_INGREDIENTS_TITLE = By.xpath(".//h2[contains(text(),'Начинки')]");
    public static final By SECTION_SAUCES_TITLE = By.xpath(".//h2[contains(text(),'Соусы')]");
    public static final By SECTION_BUNS_TITLE = By.xpath(".//h2[contains(text(),'Булки')]");


    @Step("Переход на главную страницу")
    public void openMainPage() {
        driver.get(MAIN_PAGE_URL);
    }


    @Step("Возвращает заголовок в разделе с начинками")
    public String textFromIngredientSection() {
        return driver.findElement(SECTION_INGREDIENTS_TITLE).getText();
    }

    @Step("Возвращает заголовок в разделе с соусами")
    public String textFromSaucesSection() {
        return driver.findElement(SECTION_SAUCES_TITLE).getText();
    }

    @Step("Возвращает заголовок в разделе с булочками")
    public String textFromBunsSection() {
        return driver.findElement(SECTION_BUNS_TITLE).getText();
    }

    @Step("Клик по кнопке перехода в раздел с начинками")
    public void clickIngredientSection() {
        driver.findElement(SECTION_INGREDIENTS).click();
    }

    @Step("Клик по кнопке перехода в раздел с соусами")
    public void clickSaucesSection() {
        driver.findElement(SECTION_SAUCES).click();
    }

    @Step("Клик по кнопке перехода в раздел с булочками")
    public void clickBunsSection() {
        driver.findElement(SECTION_BUNS).click();
    }

    @Step("Клик по кнопке перехода в личный кабнет в хэдере страницы")
    public void clickPersonalAccountButtonInHeader() {
        driver.findElement(PERSONAL_ACCOUNT).click();
    }

    @Step("Клик по кнопке перехода в личный кабнет по центру страницы")
    public void clickPersonalAccountButton() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
    }

    @Step("Клик по кнопке перехода в раздел с конструктором")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Возвращает заголовок раздела с конструктором")
    public String textConstructorTitle() {
        return driver.findElement(CONSTRUCTOR_TITLE).getText();
    }

    @Step("Клик по логотипу сайта")
    public void clickLogoButton() {
        driver.findElement(SITE_LOGO).click();
    }


}
