package ru.stellarburgers;

import io.qameta.allure.Description;
import ru.stellarburgers.pageobject.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import io.restassured.response.ValidatableResponse;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class UserRegistrationTest {
    private String userEmail = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private String invalidUserPassword = RandomStringUtils.randomAlphabetic(5);
    private String userPassword = RandomStringUtils.randomAlphabetic(6);
    private String userName = RandomStringUtils.randomAlphabetic(10);
    private String accessToken;
    private int statusCode;
    private static UserClientApi userClient;

    @After
    public void tearDown() {
        userClient = new UserClientApi();
        ValidatableResponse responseLogin = userClient.loginUser(userEmail, userPassword);
        statusCode = responseLogin.extract().statusCode();
        if (statusCode == 200) {
            accessToken = responseLogin.extract().path("accessToken");
            if (accessToken != null) {
                userClient.deleteUser(accessToken.substring(7));
            }
        }
    }

    @Test
    @DisplayName("Регистрация пользователя с корректным паролем")
    @Description("Успешный регистрация пользователя с корректным паролем")
    public void registrationUserValidPasswordTest() {
        RegistrationPageObject registrationPage = open(RegistrationPageObject.REGISTER_URL, RegistrationPageObject.class);
        registrationPage.isElementVisible(registrationPage.registerButton);
        registrationPage.setName(userName);
        registrationPage.setEmail(userEmail);
        registrationPage.setPassword(userPassword);
        registrationPage.registerButtonClick();
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
    }

    @Test
    @DisplayName("Регистрация пользователя с некорректным паролем")
    @Description("Неуспешная регистрация пользователя с некорректным паролем, возникновение валидации")
    public void registrationUserInvalidPasswordTest() {
        RegistrationPageObject registrationPage = open(RegistrationPageObject.REGISTER_URL, RegistrationPageObject.class);
        registrationPage.isElementVisible(registrationPage.registerButton);
        registrationPage.setName(userName);
        registrationPage.setEmail(userEmail);
        registrationPage.setPassword(invalidUserPassword);
        registrationPage.registerButtonClick();
        assertTrue("Регистрация пользователя с невалидным паролем успешно выполняется", registrationPage.invalidPasswordMessage());
    }
}