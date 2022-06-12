package ru.stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.http.ContentType.JSON;

public class UserClientApi  {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    private static final String REGISTER_PATH = "/auth/register";
    private static final String USER_PATH = "/auth/user";
    private static final String LOGIN_PATH = "/auth/login";


    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(String email, String password, String name) {
        return given()
                .spec(getBaseSpec())
                .body("{\"email\": \"" + email + "\","
                        + "\"password\": \"" + password + "\","
                        + "\"name\": \"" + name + "\"}")
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_PATH)
                .then()
                .assertThat()
                .statusCode(202);
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(String email, String password) {
        return given()
                .spec(getBaseSpec())
                .body("{\"email\":\"" + email + "\"," + "\"password\":\"" + password + "\"}")
                .when()
                .post(LOGIN_PATH)
                .then();
    }
}
