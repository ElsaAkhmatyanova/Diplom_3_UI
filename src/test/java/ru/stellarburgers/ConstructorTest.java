package ru.stellarburgers;

import ru.stellarburgers.pageobject.MainPageObject;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    @Test
    @DisplayName("Переход в раздел Начинки")
    @Description("Успешный переход в раздел Начинки. Проверка видимости позиций при переходе в раздел Начинки")
    public void transitionToFillingTest() {
        MainPageObject mainPage = open(MainPageObject.MAIN_URL, MainPageObject.class);
        mainPage.fillingsButtonClick();
        assertTrue("Позиция раздела Начинки не виден при переходе в раздел Начинки",
                mainPage.isMolluskFillingVisible());
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    @Description("Успешный переход в раздел Булки. Проверка видимости позиций при переходе в раздел Булки")
    public void transitionToBunTest() {
        MainPageObject mainPage = open(MainPageObject.MAIN_URL, MainPageObject.class);
        mainPage.saucesButtonClick();
        mainPage.fillingsButtonClick();
        mainPage.bunsButtonClick();
        assertTrue("Позиция раздела Булки не виден при переходе в раздел Булки",
                mainPage.isFluorescentBunVisible());
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    @Description("Успешный переход в раздел Соусы. Проверка видимости позиций при переходе в раздел Соусы")
    public void transitionToSauceTest() {
        MainPageObject mainPage = open(MainPageObject.MAIN_URL, MainPageObject.class);
        mainPage.saucesButtonClick();
        assertTrue("Позиция раздела Соусы не виден при переходе в раздел Соусы",
                mainPage.isSauceSpicyXVisible());
    }
}