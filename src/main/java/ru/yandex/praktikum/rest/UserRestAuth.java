package ru.yandex.praktikum.rest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.configuration.Rest;
import ru.yandex.praktikum.model.UserLogin;
import ru.yandex.praktikum.model.UserProfile;

import static io.restassured.RestAssured.given;

public class UserRestAuth extends Rest {


    @Step("Obtain access token - Получение токена")
    public String accessToken(ValidatableResponse response) {
        return response
                .extract()
                .path("accessToken");
    }

    @Step("Send POST Request to /auth/register - create User. Создание пользователя")
    public ValidatableResponse registerUser(UserProfile userProfile) {
        return given()
                .spec(getBaseRequestSpec())
                .body(userProfile)
                .when()
                .post("/auth/register")
                .then().log().all();
    }

    @Step("Send POST Request to /auth/login - Login as User. Логин под пользователем")
    public ValidatableResponse loginUser(UserLogin userLogin) {
        return given()
                .spec(getBaseRequestSpec())
                .body(userLogin)
                .when()
                .post("/auth/login")
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
                .delete("/auth/user")
                .then();

    }
}
