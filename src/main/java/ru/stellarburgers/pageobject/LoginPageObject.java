package ru.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class LoginPageObject {
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    //Локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    public SelenideElement registerButton;

    //Локатор текста "Вход"
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Вход')]")
    public SelenideElement entryText;

    //Локатор инпута "Email"
    @FindBy(how = How.XPATH, using = "(.//input[@class=\"text input__textfield text_type_main-default\"])[1]")
    public SelenideElement emailInput;

    //локатор инпута "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@class=\"text input__textfield text_type_main-default\"][@type=\"password\"]")
    public SelenideElement passwordInput;

    //локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти')]")
    public SelenideElement logInButton;

    //Локатор ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    public SelenideElement restorePwdText;

    //Локатор текста ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//fieldset/div/p[text()='Некорректный пароль']")
    public SelenideElement incorrectPasswordText;

    @Step("Нажать на кнопку Зарегистрироваться")
    public void registerButtonClick() {
        registerButton.shouldBe(visible).scrollTo().click();
    }

    @Step("Ввод значения в поле пароль")
    public void setPassword(String password) {
        passwordInput.shouldBe(visible).setValue(password);
    }

    @Step("Ввод значения в поле email")
    public void setEmail(String email) {
        emailInput.shouldBe(visible).setValue(email);
    }

    @Step("Нажать на кнопку Войти")
    public void logInButtonClick() {
        logInButton.shouldBe(visible).scrollTo().click();
    }

    @Step("Проверка видимости необходимого элемента")
    public void isElementVisible(SelenideElement element) {
        element.shouldBe(visible, Duration.ofSeconds(20));
    }
}
