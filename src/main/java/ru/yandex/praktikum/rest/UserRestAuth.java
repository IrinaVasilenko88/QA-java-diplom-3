package ru.yandex.praktikum.rest;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.configuration.Rest;
import ru.yandex.praktikum.model.UserLogin;
import ru.yandex.praktikum.model.UserProfile;

public class UserRestAuth extends Rest {

    private static final String AUTH_REGISTER = "/auth/register";
    private static final String AUTH_LOGIN = "/auth/login";
    private static final String AUTH_USER = "/auth/user";

    @Step("Obtain access token - Получение токена")
    public String accessToken(ValidatableResponse response) {
        return response
                .extract()
                .path("accessToken");
    }

    @Step("Send POST Request to /auth/register - create User. Создание пользователя")
    public ValidatableResponse registerUser(UserProfile userProfile) {
        return RestAssured.given()
                .spec(getBaseRequestSpec())
                .body(userProfile)
                .when()
                .post(AUTH_REGISTER)
                .then().log().all();
    }

    @Step("Send POST Request to /auth/login - Login as User. Логин под пользователем")
    public ValidatableResponse loginUser(UserLogin userLogin) {
        return RestAssured.given()
                .spec(getBaseRequestSpec())
                .body(userLogin)
                .when()
                .post(AUTH_LOGIN)
                .then().log().all();
    }

    public String extractToken(String token) {
        return token.substring(7);
    }

    @Step("Send DELETE Request to /auth/user - Delete User. Удаление пользователя по токену")
    public void deleteUser(String token) {
        getBaseRequestSpec()
                .header("Authorization", token)
                .when()
                .delete(AUTH_USER)
                .then();

    }
}
