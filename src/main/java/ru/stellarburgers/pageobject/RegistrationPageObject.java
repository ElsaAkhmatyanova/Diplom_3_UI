package ru.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;

public class RegistrationPageObject {
    public static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";

    //Локатор инпута "Имя"
    @FindBy(how = How.XPATH, using = "(.//input[@class=\"text input__textfield text_type_main-default\"])[1]")
    public SelenideElement nameInput;

    //Локатор инпута "Email"
    @FindBy(how = How.XPATH, using = "(.//input[@class=\"text input__textfield text_type_main-default\"])[2]")
    public SelenideElement emailInput;

    //Локатор инпута "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@class=\"text input__textfield text_type_main-default\"][@type=\"password\"]")
    public SelenideElement passwordInput;

    //Локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Зарегистрироваться')]")
    public SelenideElement registerButton;

    //Локатор сообщения о вводе некорректного пароля
    @FindBy(how = How.XPATH, using = ".//p[@class=\"input__error text_type_main-default\"]")
    public SelenideElement invalidPasswordMessage;

    //Локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Войти')]")
    public SelenideElement logInLink;

    //Локатор текста "Вход"
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Вход')]")
    public SelenideElement entryRegistrationText;

   @Step("Ввод значения в поле Имя")
    public void setName(String name) {
        nameInput.shouldBe(visible).setValue(name);
    }

    @Step("Ввод значения в поле email")
    public void setEmail(String email) {
        emailInput.shouldBe(visible).setValue(email);
    }

    @Step("Ввод значения в поле пароль")
    public void setPassword(String pass) {
        passwordInput.shouldBe(visible).setValue(pass);
    }

    @Step("Нажать на кнопку Зарегистрироваться")
    public void registerButtonClick() {
        registerButton.shouldBe(visible).scrollTo().click();
    }

    @Step("Сообщение о невалидном введенном пароле успешно отображается")
    public boolean invalidPasswordMessage() {
        return invalidPasswordMessage.shouldBe(visible).isDisplayed();
    }

    @Step("Нажать на кнопку Войти")
    public void logInButtonClick() {
        logInLink.click();
    }

    @Step("Проверка видимости необходимого элемента")
    public void isElementVisible(SelenideElement element) {
        registerButton.shouldBe(visible, Duration.ofSeconds(10));
    }
}
