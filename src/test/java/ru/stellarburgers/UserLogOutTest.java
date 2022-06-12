package ru.stellarburgers;

import io.qameta.allure.Description;
import ru.stellarburgers.pageobject.*;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UserLogOutTest {
    MainPageObject mainPage;
    AccountPageObject accountPage;

    private String userEmail = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
    private String userPassword = RandomStringUtils.randomAlphabetic(6);
    private String userName = RandomStringUtils.randomAlphabetic(10);
    private String accessToken;
    private UserClientApi userClient;

    @Before
    public void LoginUser() {
        userClient = new UserClientApi();
        ValidatableResponse createUserResponse = userClient.createUser(userEmail, userPassword, userName).statusCode(200);
        accessToken = createUserResponse.extract().path("accessToken");

        MainPageObject mainPage = open(MainPageObject.MAIN_URL, MainPageObject.class);
        mainPage.isElementVisible(mainPage.createBurgerText);
        mainPage.logInButtonClick();
        LoginPageObject loginPage = page(LoginPageObject.class);
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
        loginPage.isElementVisible(loginPage.entryText);
        loginPage.setEmail(userEmail);
        loginPage.setPassword(userPassword);
        loginPage.logInButtonClick();
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken.substring(7));
        }
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Успешный выход пользователя через личный кабинет")
    public void loginOutTest() {
        mainPage = page(MainPageObject.class);
        mainPage.isElementVisible(mainPage.createBurgerText);
        mainPage.personalAreaClick();
        accountPage = page(AccountPageObject.class);
        accountPage.exitButtonClick();
        webdriver().shouldHave(url(LoginPageObject.LOGIN_URL));
    }
}