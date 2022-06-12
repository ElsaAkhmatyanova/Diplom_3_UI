package ru.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class AccountPageObject {
    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Локатор для кнопки "Выход"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Выход')]")
    public SelenideElement exitButton;

    //Локатор кнопки "Конструктор" в шапке страницы
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Конструктор')]")
    public SelenideElement constructorButton;

    //Локатор надписи "Профиль"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Профиль')]")
    public SelenideElement profileFieldText;

    //Локатор надписи "В этом разделе вы можете изменить свои персональные данные"
    @FindBy(how = How.XPATH, using = ".//nav/p")
    public SelenideElement personalDataText;

    //Локатор кнопки с лого stellarBurgers
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    public SelenideElement stellarBurgersLogo;

    @Step("Нажать на кнопку Конструктор")
    public void constructorButtonClick() {
        constructorButton.shouldBe(visible).click();
    }

    @Step("Нажать на логотип StellarBurger")
    public void stellarBurgersLogoClick() {
        stellarBurgersLogo.shouldBe(visible).click();
    }

    @Step("Нажать на кнопку Выход")
    public void exitButtonClick() {
        exitButton.shouldBe(visible).click();
    }
}
