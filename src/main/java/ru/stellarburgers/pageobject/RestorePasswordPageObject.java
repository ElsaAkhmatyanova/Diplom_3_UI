package ru.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;

public class RestorePasswordPageObject {
    public static final String RESTORE_PWD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Локатор ссылки "Войти"
    @FindBy(how = How.CLASS_NAME,using = "Auth_link__1fOlj")
    public SelenideElement logInButton;

    //Локатор надписи "Восстановление пароля"
    @FindBy(how = How.CLASS_NAME,using = "Auth_login__3hAey")
    public SelenideElement restoreText;

    @Step("Нажать на кнопку Войти")
    public void logInButtonClick(){
        logInButton.shouldBe(visible).click();
    }

    @Step("Проверка видимости необходимого элемента")
    public void isElementVisible(SelenideElement element) {
        element.shouldBe(visible, Duration.ofSeconds(20));
    }
}
