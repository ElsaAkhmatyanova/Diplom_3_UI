package ru.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class MainPageObject {
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопки "Личный кабинет"
    @FindBy(how = How.LINK_TEXT,using = "Личный Кабинет")
    public SelenideElement personalArea;

    //Локатор кнопки "Войти"
    @FindBy(how = How.XPATH,using = ".//button[@class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg\"]")
    public SelenideElement logInButton;

    //Локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH,using = ".//button[contains(text(),'Оформить заказ')]")
    public SelenideElement makeOrderButton;

    //Локатор выхода из модального окна оформления заказа
    @FindBy(how = How.XPATH,using = ".//button[@class=\"Modal_modal__close_modified__3V5XS Modal_modal__close__TnseK\"]")
    public SelenideElement modalWindowExitButton;

    //Локатор надписи "Собери бургер"
    @FindBy(how = How.XPATH,using = ".//h1[contains(text(),'Соберите бургер')]")
    public SelenideElement createBurgerText;

    //Локатор кнопки "Булки"
    @FindBy(how = How.XPATH,using = ".//span[contains(text(),'Булки')]")
    public SelenideElement bunsButton;

    //Локатор кнопки "Соус"
    @FindBy(how = How.XPATH,using = ".//span[contains(text(),'Соусы')]")
    public SelenideElement saucesButton;

    //Локатор кнопки "Начинки"
    @FindBy(how = How.XPATH,using = ".//span[contains(text(),'Начинки')]")
    public SelenideElement fillingsButton;

    //Локатор пункта меню "Мясо бессмертных моллюсков Protostomia"
    @FindBy(how = How.XPATH,using =".//p[contains(text(),'Мясо бессмертных моллюсков Protostomia')]")
    public SelenideElement molluskFilling;

    //Локатор пункта меню "Флюоресцентная булка R2-D3"
    @FindBy(how = How.CSS,using = "[alt='Флюоресцентная булка R2-D3']")
    public SelenideElement fluorescentBun;

    //Локатор пункта меню "Соус Spicy-X"
    @FindBy(how = How.XPATH,using = ".//p[contains(text(),'Соус Spicy-X')]")
    public SelenideElement sauceSpicyX;

    @Step("Нажать на кнопку Личный кабинет")
    public void personalAreaClick(){
        personalArea.shouldBe(visible).scrollTo().click();
    }

    @Step("Нажать на кнопку Войти")
    public void logInButtonClick(){
        logInButton.shouldBe(visible).click();
    }

    @Step("Закрыть модальное окно заказа, в случае если оно всплывает")
    public void modalWindowExitButtonClick(){
        if(modalWindowExitButton.isDisplayed())
            modalWindowExitButton.click();
        personalArea.shouldBe(visible).scrollTo();
    }

    @Step("Текст Собери бургер успешно отображается")
    public boolean isCreateBurgerTextVisible(){
        return createBurgerText.shouldBe(visible).isDisplayed();
    }

    @Step("Нажать на кнопку Булки")
    public void bunsButtonClick(){
        bunsButton.shouldBe(visible).click();
    }

    @Step("Нажать на кнопку Соусы")
    public void saucesButtonClick(){
        saucesButton.shouldBe(visible).click();
    }

    @Step("Нажать на кнопку Начинки")
    public void fillingsButtonClick(){
        fillingsButton.shouldBe(visible).click();
    }

    @Step("Позиция заказа Мясо бессмертных моллюсков Protostomia успешно отображается")
    public boolean isMolluskFillingVisible(){
        return molluskFilling.is(visible);
    }

    @Step("Позиция заказа Spicy-X успешно отображается")
    public boolean isSauceSpicyXVisible(){
        return sauceSpicyX.is(visible);
    }

    @Step("Позиция заказа Флюоресцентная булка R2-D3 успешно отображается")
    public boolean isFluorescentBunVisible(){
        return fluorescentBun.is(visible);
    }

    @Step("Проверка видимости необходимого элемента")
    public boolean isElementVisible(SelenideElement element) {
        element.shouldBe(visible, Duration.ofSeconds(20));
        return false;
    }

    @Step("Проверка отображения")
    public boolean isElementDisplayed(SelenideElement element) {
        return element.shouldBe(visible).isDisplayed();
    }
}
