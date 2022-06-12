package ru.stellarburgers;

import io.qameta.allure.Description;
import ru.stellarburgers.pageobject.*;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.WebDriverConditions.url;

public class HeaderTest {
    MainPageObject mainPage;
    AccountPageObject accountPage;
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

    @Before
    public void loginUser() {
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
    @DisplayName("Переход в личный кабинет по клику на кнопку в шапке сайта")
    @Description("Успешный переход на страницу личного кабинета при нажатии на кнопку в шапке сайта")
    public void transitionToAccountAreaTest() {
        mainPage = page(MainPageObject.class);
        mainPage.isElementVisible(mainPage.createBurgerText);
        mainPage.personalAreaClick();
        accountPage = page(AccountPageObject.class);
        webdriver().shouldHave(url(accountPage.PROFILE_URL));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на кнопку 'Конструктор'")
    @Description("Успешный переход на страницу личного кабинета при нажатии на кнопку 'Конструктор' в шапке сайта")
    public void transitionToConstructorButtonTest() {
        mainPage = page(MainPageObject.class);
        mainPage.isElementVisible(mainPage.createBurgerText);
        mainPage.personalAreaClick();
        accountPage = page(AccountPageObject.class);
        accountPage.constructorButtonClick();
        mainPage = page(MainPageObject.class);
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на лого сайта")
    @Description("Успешный переход на страницу личного кабинета при нажатии на лого сайта")
    public void transitionToConstructorLogoTest() {
        mainPage = page(MainPageObject.class);
        mainPage.isElementVisible(mainPage.createBurgerText);
        mainPage.personalAreaClick();
        accountPage = page(AccountPageObject.class);
        accountPage.stellarBurgersLogoClick();
        mainPage = page(MainPageObject.class);
        webdriver().shouldHave(url(MainPageObject.MAIN_URL));
    }
}
