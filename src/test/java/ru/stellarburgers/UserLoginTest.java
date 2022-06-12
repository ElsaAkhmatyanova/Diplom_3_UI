package ru.stellarburgers;

import io.qameta.allure.Description;
import ru.stellarburgers.pageobject.*;

import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UserLoginTest {
    RegistrationPageObject registrationPage;
    MainPageObject mainPage;
    LoginPageObject loginPage;
    RestorePasswordPageObject restorePage;
    private static String userEmail = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private static String userPassword = RandomStringUtils.randomAlphabetic(6);
    private static String userName = RandomStringUtils.randomAlphabetic(10);
    private static String accessToken;
    private static UserClientApi userClient;

    @BeforeClass
    public static void createUser() {
        userClient = new UserClientApi();
        ValidatableResponse createUserResponse = userClient.createUser(userEmail, userPassword, userName).statusCode(200);
        accessToken = createUserResponse.extract().path("accessToken");
    }

    @After
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void deleteUser() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken.substring(7));
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("Успешная авторизация пользователя при переходе по кнопке 'Войти в аккаунт'")
    public void loginMainPageTest() {
        mainPage = open(MainPageObject.MAIN_URL, MainPageObject.class);
        mainPage.isCreateBurgerTextVisible();
        mainPage.logInButtonClick();
        loginPage = page(LoginPageObject.class);
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
        loginPage.isElementVisible(loginPage.entryText);
        loginPage.setEmail(userEmail);
        loginPage.setPassword(userPassword);
        loginPage.logInButtonClick();
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
        assertTrue("Вход не успешен", mainPage.isElementDisplayed(mainPage.makeOrderButton));
    }

    @Test
    @DisplayName("Вход через «Личный кабинет»")
    @Description("Успешная авторизация пользователя при переходе через личный кабинет")
    public void loginAccountAreaTest() {
        mainPage = open(MainPageObject.MAIN_URL, MainPageObject.class);
        mainPage.isCreateBurgerTextVisible();
        mainPage.personalAreaClick();
        loginPage = page(LoginPageObject.class);
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
        loginPage.isElementVisible(loginPage.entryText);
        loginPage.setEmail(userEmail);
        loginPage.setPassword(userPassword);
        loginPage.logInButtonClick();
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
        assertTrue("Вход не успешен", mainPage.isElementDisplayed(mainPage.makeOrderButton));
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Успешная авторизация пользователя через форму регистрации")
    public void loginRegistrationFormTest() {
        registrationPage = open(RegistrationPageObject.REGISTER_URL, RegistrationPageObject.class);
        registrationPage.isElementVisible(registrationPage.registerButton);
        registrationPage.logInButtonClick();
        loginPage = page(LoginPageObject.class);
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
        loginPage.isElementVisible(loginPage.entryText);
        loginPage.setEmail(userEmail);
        loginPage.setPassword(userPassword);
        loginPage.logInButtonClick();
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
    }

    @Test
    @DisplayName("Вход через восстановление пароля")
    @Description("Успешная авторизация пользователя через восстановление пароля")
    public void loginRestorePwdTest() {
        restorePage = open(RestorePasswordPageObject.RESTORE_PWD_URL, RestorePasswordPageObject.class);
        restorePage.isElementVisible(restorePage.restoreText);
        restorePage.logInButtonClick();
        loginPage = page(LoginPageObject.class);
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
        loginPage.isElementVisible(loginPage.entryText);
        loginPage.setEmail(userEmail);
        loginPage.setPassword(userPassword);
        loginPage.logInButtonClick();
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
    }
}